package com.apiauthentication.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationModel {
	
	@NotNull
	@NotEmpty(message="Name cannot be empty")
	private String fullName;
	
	@NotNull
	@NotEmpty(message="Email cannot be empty")
	private String email;
	
	@NotNull
	@NotEmpty(message="Password cannot be empty")
	private String password;

}
