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
	private Date eventDate;
	private String concept;
	private String photoE;
	
	
	@Enumerated(EnumType.STRING)
	private DonationType donationtype;
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Advertising> advertisings;
	

}
