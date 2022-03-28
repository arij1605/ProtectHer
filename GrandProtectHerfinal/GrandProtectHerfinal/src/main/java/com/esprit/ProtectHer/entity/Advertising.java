
package com.esprit.ProtectHer.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
public class Advertising   implements Serializable   {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAdvertising; // Clé primaire
	
	@NotNull
	@Size(min=2, max=10)
	private String Description;
	
	
	private String photo;
	
	
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date AdvertisingDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	User users;
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "advertisings")
	@JsonIgnore
	private List<Event> events;
	
	
	
	
	/*@OneToMany(cascade = CascadeType.ALL, mappedBy="patient")
	private List<RendezVous> rendezVous;*/
	

}
