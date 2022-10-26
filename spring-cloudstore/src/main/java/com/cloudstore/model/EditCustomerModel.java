package com.cloudstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditCustomerModel {
	
	//email is used to identify the customer
	private String email;
	private String mobile;
	private String imageUrl;
	private String houseName;
	private String streetName;
	private String cityName;
	private String pincode;

}
