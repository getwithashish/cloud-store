package com.apiauthentication.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginModel {
	
	@NotNull
	@NotEmpty(message="Email cannot be empty")
	@Email(regexp="[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message="Email should be valid")
	private String email;
	
	@NotNull
	@NotEmpty(message="Password cannot be empty")
	@Pattern(regexp="^[a-zA-Z0-9]{8,16}$")
	private String password;

}
