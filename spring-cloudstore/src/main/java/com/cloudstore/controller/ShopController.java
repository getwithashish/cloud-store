package com.cloudstore.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.cloudstore.entity.ShopEntity;
import com.cloudstore.service.ShopServiceInterface;
import com.cloudstore.utility.JWTExtractor;
import com.cloudstore.utility.JWTUtility;


public class ShopController {

	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private ShopServiceInterface shopService;

	@Autowired
	private JWTExtractor jwtExtractor;

	@CrossOrigin("http://localhost:3000")
	@GetMapping("/user/shop")
	public ShopEntity shopInfo(HttpServletRequest request) {
		String token = jwtExtractor.extractTokenFromHeader(request);
		String email = jwtUtility.getUsernameFromToken(token);
		ShopEntity shop = shopService.shopInfo(email);
		return shop;
	}

//	protected Object addUser() {
//		// TODO Auto-generated method stub
//		return super.addUser();
//	}

	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("/user/shop")
	public ShopEntity disableShop(HttpServletRequest request) {
		String token = jwtExtractor.extractTokenFromHeader(request);
		String email = jwtUtility.getUsernameFromToken(token);
		ShopEntity shop = shopService.disableShop(email);
		return shop;
	}

}
