package com.cloudstore.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
	private boolean enabled = false;

}
