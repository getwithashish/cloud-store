package com.cloudstore.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudstore.entity.ProductEntity;
import com.cloudstore.entity.ShopEntity;
import com.cloudstore.model.EditShopModel;
import com.cloudstore.model.ProductDeleteModel;
import com.cloudstore.model.ProductModel;
import com.cloudstore.repository.ProductRepository;
import com.cloudstore.service.ShopServiceInterface;
import com.cloudstore.utility.JWTExtractor;
import com.cloudstore.utility.JWTUtility;


@RestController
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
		Authentication usernamePasswordAuthenticationToken = 
				SecurityContextHolder.getContext().getAuthentication();
		String email = usernamePasswordAuthenticationToken.getName();
		ShopEntity shop = shopService.shopInfo(email);
		return shop;
	}
	
	
//	TODO Later, maybe add API for adding multiple products at the same time
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/user/shop/product")
	public ProductEntity addProduct(@RequestBody ProductModel productModel) {
		ProductEntity product = shopService.addProduct(productModel);
		return product;
	}
	
	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("/user/shop/product")
	public String disableProduct(@RequestBody ProductDeleteModel productDeleteModel) {
		String[] prodNames = productDeleteModel.getProdNames();
		shopService.disableProducts(prodNames);
		return "SUCCESSFULLY DISABLED: \n" + prodNames;
	}

	@CrossOrigin("http://localhost:3000")
	@PutMapping("/user/shop")
	public ShopEntity editShop(@RequestBody EditShopModel editModel) {
		ShopEntity shop = shopService.editShop(editModel);
		return shop;
	}

	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("/user/shop")
	public ShopEntity disableShop(HttpServletRequest request) {
		String token = jwtExtractor.extractTokenFromHeader(request);
		String email = jwtUtility.getUsernameFromToken(token);
		ShopEntity shop = shopService.disableShop(email);
		return shop;
	}

}
