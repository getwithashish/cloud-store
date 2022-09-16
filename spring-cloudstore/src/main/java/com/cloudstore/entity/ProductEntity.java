package com.cloudstore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "product_collection")
@Data
public class ProductEntity {
	
	@Id
	private String id;
	private String prodName;
	private String category;
	private String image;
	private String mainUnit;
	private String saleUnit;
	private double weight;
	private double price;
	private int increment;
	private boolean enabled = true;

}
