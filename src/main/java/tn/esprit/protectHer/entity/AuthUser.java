package tn.esprit.protectHer.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AuthUser {

	@NotBlank
	@Size(min = 6)
	String password;
	
	@NotBlank
	@Size(min = 3, max = 20)
	String userName;

}