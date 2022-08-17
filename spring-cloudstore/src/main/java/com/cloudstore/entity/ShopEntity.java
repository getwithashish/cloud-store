package com.cloudstore.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection = "shop_collection")
@Data
public class ShopEntity {
	
	@Id
	private String id;
	private String fullName;
	private String email;
	private String ownerFullName;
	private String role;
	private boolean enabled = true;
	private String streetName;
	private String cityName;
	private String stateName;

}
