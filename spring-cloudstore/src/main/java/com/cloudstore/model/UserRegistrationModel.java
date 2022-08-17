package com.cloudstore.model;


import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationModel {

	@NotEmpty(message = "Name cannot be empty")
	private String fullName;

	@NotEmpty(message = "Email cannot be empty")
	private String email;

	@NotEmpty(message = "Password cannot be empty")
	private String password;

	@NotEmpty(message = "Role cannot be empty")
	private String role;

}
