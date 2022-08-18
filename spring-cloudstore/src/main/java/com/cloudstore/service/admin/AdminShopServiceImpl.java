package com.cloudstore.service.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cloudstore.entity.ShopEntity;
import com.cloudstore.repository.ShopRepository;
import com.cloudstore.service.authentication.UserLoginServiceInterface;


public class AdminShopServiceImpl implements AdminShopServiceInterface {

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private UserLoginServiceInterface userLoginService;

	@Override
	public List<ShopEntity> findAllShops() {
		List<ShopEntity> shops = shopRepository.findAll();
		return shops;
	}

	@Override
	public void disableShops(String[] emails) {
		List<ShopEntity> shops = shopRepository.findAllByEmails(emails);
		for (ShopEntity shop : shops) {
			shop.setEnabled(false);
		}
		shopRepository.saveAll(shops);

		userLoginService.disableUsers(emails);
	}

}
