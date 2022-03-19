package com.esprit.ProtectHer.entity;

import java.io.Serializable;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Clé primaire
	private String FirstName;
	private String LastName;
	private String Adress;
	private String BirthDate;
	private String mail;
	

	@Enumerated(EnumType.STRING)
	private Role role;
	
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
