package com.cloudstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditShopModel {
	
	//email is used to identify the customer
		private String email;
		private String ownerFullName;
		private String mobile;
		private String imageUrl;
		private String documentUrl;
		private String streetName;
		private String cityName;
		private String pincode;

}
