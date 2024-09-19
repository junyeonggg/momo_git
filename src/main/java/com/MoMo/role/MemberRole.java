package com.MoMo.role;

import lombok.Getter;

@Getter
public enum MemberRole {
	ADMIN("ROLE_ADMIN"),
	OWNER("ROLE_OWNER"),
	USER("ROLE_USER");
	
	private String value;
//	
	MemberRole(String value){
		this.value=value;
	}
}
