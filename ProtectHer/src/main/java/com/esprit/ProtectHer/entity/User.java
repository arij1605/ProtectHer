package com.esprit.ProtectHer.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor

@AllArgsConstructor
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long  id;
	
	@NonNull
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	

	
	@NonNull
	private String firstName;
	
	@NonNull
	private String lastName;
	
	@NonNull
	private String adress;
	
	private String email;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private Role role ;

	@ManyToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL,mappedBy = "user")
	@JsonIgnore
	private Set<Training> training ;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
	@JsonIgnore
	private Set<Certification> certification;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<JackPot> jackpots;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="users")
	@JsonIgnore
	private Set<Advertising> advertising;
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "users")
	@JsonIgnore
	private List<Event> events;

	


}
