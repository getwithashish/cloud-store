package com.cloudstore.service.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cloudstore.entity.CustomerEntity;
import com.cloudstore.entity.EnableStatusEnum;
import com.cloudstore.entity.ShopEntity;
import com.cloudstore.entity.UserAuthenticationEntity;
import com.cloudstore.model.EditShopModel;
import com.cloudstore.repository.ShopRepository;
import com.cloudstore.repository.UserAuthenticationRepository;
import com.cloudstore.service.authentication.UserLoginServiceInterface;


@Service
public class AdminShopServiceImpl implements AdminShopServiceInterface {

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private UserLoginServiceInterface userLoginService;
	
	@Autowired
	private UserAuthenticationRepository userAuthenticationRepository;

	@Override
	public List<ShopEntity> findAllShops() {
		List<ShopEntity> shops = shopRepository.findAll();
		return shops;
	}

	@Override
	public void disableShops(String[] emails) {
		List<ShopEntity> shops = shopRepository.findAllByEmails(emails);
		for (ShopEntity shop : shops) {
			shop.setEnableStatus(EnableStatusEnum.ADMIN_DISABLED);
		}
		shopRepository.saveAll(shops);

		userLoginService.disableUsers(emails);
	}

	@Override
	public void enableShops(String[] emails) {
		List<ShopEntity> shops = shopRepository.findAllByEmails(emails);
		for (ShopEntity shop : shops) {
			shop.setEnableStatus(EnableStatusEnum.ENABLED);
		}
		shopRepository.saveAll(shops);

		userLoginService.enableUsers(emails);
		
	}

	@Override
	public ShopEntity editShops(EditShopModel editModel) {
		ShopEntity shop = shopRepository.findByEmail(editModel.getEmail());
		if(shop != null) {
			
//			shop.setFullName(editModel.getFullName());
//			shop.setRole(editModel.getRole());
//			shop.setHouseName(editModel.getHouseName());
			shop.setOwnerFullName(editModel.getOwnerFullName());
			shop.setMobile(editModel.getMobile());
			shop.setImageUrl(editModel.getImageUrl());
			shop.setDocumentUrl(editModel.getDocumentUrl());
			shop.setStreetName(editModel.getStreetName());
			shop.setCityName(editModel.getCityName());
			shop.setPincode(editModel.getPincode());
			
			shopRepository.save(shop);
			
//			UserAuthenticationEntity user = userAuthenticationRepository.findByEmail(editModel.getEmail());
//			user.setFullName(editModel.getFullName());
//			user.setRole(editModel.getRole());
//			
//			userAuthenticationRepository.save(user);
		}
		else {
			throw new UsernameNotFoundException("Customer with that email does not exist");
		}
		return shop;
	}

}
