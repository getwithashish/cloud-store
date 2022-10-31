package com.cloudstore.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection = "order_collection")
@Data
public class OrderEntity {
	
	@Id
	private String id;
	private String custId;
	private List<OrderProductList> products;
	private double totalAmt;
	private long time;
	private OrderStatusEnum status = OrderStatusEnum.PENDING;
	private String txnToken;
	private String addressId;

}

