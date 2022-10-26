package com.cloudstore.entity;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Data;


@Document(collection = "authentication_collection")
@Data
public class UserAuthenticationEntity {

	@Id
	private String id;
	private String fullName;
	private String email;
	private String password;
	private String role;
	private EnableStatusEnum enableStatus = EnableStatusEnum.DISABLED;
	
	public Collection<? extends GrantedAuthority> getAuthorities(List<String> role){
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String singleRole : role) {
			authorities.add(new SimpleGrantedAuthority(singleRole));
		}
		return authorities;
	}

}
