package com.cloudstore.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudstore.entity.CustomerEntity;
import com.cloudstore.entity.OrderEntity;
import com.cloudstore.entity.OrderProductList;
import com.cloudstore.entity.ProductEntity;
import com.cloudstore.model.AddressModel;
import com.cloudstore.model.OrderModel;
import com.cloudstore.model.OrderProductListModel;
import com.cloudstore.service.CustomerServiceInterface;
import com.cloudstore.service.ShopServiceInterface;
import com.cloudstore.utility.PaytmInitiateTransaction;

import io.jsonwebtoken.io.IOException;

@RestController
public class PaymentController {
	
	@Autowired
	private CustomerServiceInterface customerService;
	
	@Autowired
	private ShopServiceInterface shopService;
	
	@Autowired
	private PaytmInitiateTransaction paytmInitiate;
	
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/user/customer/payment")
	public JSONObject createOrder(@RequestBody OrderModel orderModel) throws Exception {
		Authentication usernamePasswordAuthenticationToken =
				SecurityContextHolder.getContext().getAuthentication();
		String email = usernamePasswordAuthenticationToken.getName();
		CustomerEntity customer = customerService.customerInfo(email);
		
		System.out.println("Address Received: " + orderModel.getAddressId());
		
		String[] orderIdAndAmount = shopService.createOrder(customer, orderModel);
		
		String response = paytmInitiate.initiateTransaction(orderIdAndAmount[0], orderIdAndAmount[1]);
		//TODO Try to convert response into JSONObject and return only the txnToken
		
		JSONParser jsonParser = new JSONParser();
		JSONObject responseJSON = (JSONObject) jsonParser.parse(response);
		responseJSON.put("orderId", orderIdAndAmount[0]);
		return responseJSON;
	}
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/user/customer/deliveryaddress")
	public String storeDeliveryAddress(@RequestBody AddressModel address) {
		
		String addressId = customerService.storeDeliveryAddress(address);
		return addressId;
	}
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/user/customer/paymentResponse")
	public void paymentResponse(HttpServletRequest request, HttpServletResponse response) throws java.io.IOException {
		String responseMsg = request.getParameter("RESPMSG");
		String responseCode = request.getParameter("RESPCODE");
		String orderId = request.getParameter("ORDERID");
		
		if(responseCode.equalsIgnoreCase("01")) {
			response.sendRedirect("http://localhost:3000/cart/success?orderId=" + orderId);
		}
		else {
			response.sendRedirect("http://localhost:3000/cart/failure?errorMessage=" + responseMsg);
		}
	}

}
