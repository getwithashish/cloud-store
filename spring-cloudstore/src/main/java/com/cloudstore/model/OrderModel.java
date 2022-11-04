package com.cloudstore.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
	
	private List<OrderProductListModel> orderProductList;
	private String addressId;

}
