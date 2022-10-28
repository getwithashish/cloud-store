package com.cloudstore.entity;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection = "shop_collection")
@Data
public class ShopEntity {

	@Id
	private String id;
	private String shopName;
	private String email;
	private String ownerFullName;
	private String role;
	private String mobile;
	private String imageUrl;
	private EnableStatusEnum enableStatus = EnableStatusEnum.ENABLED;
	private boolean verifyStatus = false;
	private String documentUrl;
	private String streetName;
	private String cityName;
	private String pincode;
	private List<String> productId;

}
