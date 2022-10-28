package com.cloudstore.model;

import java.util.List;

import com.cloudstore.entity.ProductShopEntryEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
	
//	private List<ProductShopEntryEntity> shops;
	private String pincode;
	private int stock;
	private String prodName;
	private String category;
	private String imageUrl;
	private String mainUnit;
	private String saleUnit;
	private double weight;
	private double price;
	private int increment;

}
