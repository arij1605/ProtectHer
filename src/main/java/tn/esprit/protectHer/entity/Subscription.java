package tn.esprit.protectHer.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@NoArgsConstructor
@AllArgsConstructor

public class Subscription implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long subscriptionId;
	
	private Boolean converted;
	
	private String email;
	
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	private String firstName;
	
	private String lastName;
	
	private Long nic;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JsonIgnore
	private User owner;
	
	private Float price;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
}
