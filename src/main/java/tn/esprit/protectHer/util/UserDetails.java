package tn.esprit.protectHer.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tn.esprit.protectHer.entity.User;

@Getter
@AllArgsConstructor
public class UserDetails {
	
	private User user;
	
	private String accessToken;

}
