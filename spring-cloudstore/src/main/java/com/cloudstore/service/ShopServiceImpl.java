package com.cloudstore.service;


import org.springframework.beans.factory.annotation.Autowired;

import com.cloudstore.entity.ShopEntity;
import com.cloudstore.repository.ShopRepository;
import com.cloudstore.service.authentication.UserLoginServiceInterface;


public class ShopServiceImpl implements ShopServiceInterface {

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private UserLoginServiceInterface userLoginService;

	@Override
	public ShopEntity shopInfo(String email) {
		ShopEntity shop = shopRepository.findByEmail(email);
		return shop;
	}

	@Override
	public ShopEntity disableShop(String email) {
		ShopEntity shop = shopRepository.findByEmail(email);
		shop.setEnabled(false);
		shopRepository.save(shop);

		userLoginService.disableUser(email);

		return shop;
	}

}
