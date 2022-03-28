package tn.esprit.protectHer.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
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

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long userId;

	public User(@NotBlank @Size(min = 6) String password, @NotBlank @Size(min = 3, max = 20) String userName) {
		super();
		this.password = password;
		this.userName = userName;
	}

	private String address;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	private String firstName;

	@OneToMany(mappedBy = "sender", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Invitation> invitations;

	private String lastName;
	
	private Boolean active;
	
	private Integer verificationCode;

	@Enumerated(EnumType.STRING)
	private GenderType gender;

	@NotBlank
	@Size(min = 6)
	private String password;

	private String picturePath;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Role> roles;

	@OneToOne(mappedBy = "owner", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JsonIgnore
	private Subscription subscription;

	@NotBlank
	@Size(min = 3, max = 20)
	private String userName;

}
