package com.cloudstore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection = "address_collection")
@Data
public class AddressEntity {
	
	@Id
	private String id;
	private String fullName;
	private String email;
	private String mobile;
	private String houseName;
	private String place;
	private String pincode;

}
