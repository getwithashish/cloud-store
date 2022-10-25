package com.cloudstore.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection = "customer_collection")
@Data
public class CustomerEntity {

	@Id
	private String id;
	private String fullName;
	private String email;
//	private String password;
	private String role;
	private boolean enabled = true;
	private String houseName;
	private String streetName;
	private String cityName;
	private String stateName;

}
