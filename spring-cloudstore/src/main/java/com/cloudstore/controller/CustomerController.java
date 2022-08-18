package com.cloudstore.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudstore.entity.CustomerEntity;
import com.cloudstore.service.CustomerServiceInterface;
import com.cloudstore.utility.JWTExtractor;
import com.cloudstore.utility.JWTUtility;


@RestController
public class CustomerController {

	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private CustomerServiceInterface customerService;

	@Autowired
	private JWTExtractor jwtExtractor;

	@CrossOrigin("http://localhost:3000")
	@GetMapping("/user/customer")
	public CustomerEntity customerInfo(HttpServletRequest request) {
		String token = jwtExtractor.extractTokenFromHeader(request);
		String email = jwtUtility.getUsernameFromToken(token);
		CustomerEntity customer = customerService.customerInfo(email);
		return customer;
	}

//	protected Object addUser() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("/user/customer")
	public CustomerEntity disableCustomer(HttpServletRequest request) {
		String token = jwtExtractor.extractTokenFromHeader(request);
		String email = jwtUtility.getUsernameFromToken(token);
		CustomerEntity customer = customerService.disableCustomer(email);
		return customer;
	}

}
