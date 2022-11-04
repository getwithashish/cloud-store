package com.cloudstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductList {
	
	private String prodId;
	private String shopId;
	private int quantity;
	private double price;

}
