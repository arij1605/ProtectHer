package tn.esprit.protectHer.entity;

import org.springframework.security.core.GrantedAuthority;

public enum RoleType implements GrantedAuthority {
	ADMIN,ASSOCIATION,DONOR,FORMER,LAWYER,PSYCHOTHERAPIST;
	
	@Override
	public String getAuthority() {
		return "ROLE_" + name();
	}
}
