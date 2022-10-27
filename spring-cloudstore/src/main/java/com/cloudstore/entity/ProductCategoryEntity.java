package com.cloudstore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "category_collection")
@Data
public class ProductCategoryEntity {
	
	@Id
	private String id;
	private String category;

}
