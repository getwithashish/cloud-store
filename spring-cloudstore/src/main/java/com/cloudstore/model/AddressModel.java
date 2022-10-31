package com.cloudstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressModel {

	private String fullName;
	private String email;
	private String mobile;
	private String houseName;
	private String place;
	private String pincode;

}
