package com.cloudstore.Model;

import com.cloudstore.model.ProductModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockUpdateModel {
	
	private String prodId;
	private int stock;

}
