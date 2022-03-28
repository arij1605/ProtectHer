package com.esprit.ProtectHer.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEvent; // Clé primaire
	
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date eventDate;
	@NotNull
	@Size(min=2, max=10)
	private String concept;
	@NotNull
	private int capacite;
	
	private String photoE;
	
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private DonationType donationtype;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Advertising> advertisings;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<User> users;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<JackPot> jackpots;
	
	
	

}
