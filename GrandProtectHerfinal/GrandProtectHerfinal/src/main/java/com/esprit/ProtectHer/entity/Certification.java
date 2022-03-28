package com.esprit.ProtectHer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@AllArgsConstructor
@NoArgsConstructor
public class Certification implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCertification")
	private long  idCertification;
	
	@NonNull
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@NonNull
	private String domaine;
	 
	private Boolean conversion ;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	Training training;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	User user;
	
	
	
	

}
