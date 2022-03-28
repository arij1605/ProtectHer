package com.esprit.ProtectHer.entity;

import java.io.Serializable;
import java.util.Date;
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
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Training implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTraining")
	private long  idTraining;
	
	@NonNull
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	
	@NonNull
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	
	@NonNull
	private String domaine;
	
	@NonNull
	private Integer nbHeure;
	
	@NonNull
	private Integer nbMaxParticipant;
	
	@NonNull
	private Integer frais;

	private String photo;
	
	
	
	private long raiting ;
	
	private Boolean etat;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private Level level;
	
	
	@ManyToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<User> user ;

	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Certification> certification;

}
