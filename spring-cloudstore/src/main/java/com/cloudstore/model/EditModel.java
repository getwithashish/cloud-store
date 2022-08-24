package com.cloudstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditModel {
	
	String email;
	String fullName;
	String role;
	String houseName;
	String streetName;
	String cityName;
	String stateName;

}
