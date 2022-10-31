package com.cloudstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductListModel {
	
	private String prodId;
	private String shopId;
	private int quantity;
	private double price;

}
