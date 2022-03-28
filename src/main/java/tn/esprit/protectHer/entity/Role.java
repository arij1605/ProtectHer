package tn.esprit.protectHer.entity;

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

public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long roleId;
	
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@ManyToMany(mappedBy= "roles", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<User> users;

}
