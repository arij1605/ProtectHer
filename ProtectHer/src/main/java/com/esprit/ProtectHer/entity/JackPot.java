package com.esprit.ProtectHer.entity;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
public class JackPot implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idJackpot; // Clé primaire
	private Integer somme;
	
	@Enumerated(EnumType.STRING)
	private DonationType donationtype;
	
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "jackpots")
	@JsonIgnore
	private List<User> users;
	
	
	
}
