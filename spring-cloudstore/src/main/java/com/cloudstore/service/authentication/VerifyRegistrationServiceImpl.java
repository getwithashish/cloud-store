package com.cloudstore.service.authentication;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudstore.entity.CustomerEntity;
import com.cloudstore.entity.EnableStatusEnum;
import com.cloudstore.entity.ShopEntity;
import com.cloudstore.entity.UserAuthenticationEntity;
import com.cloudstore.entity.VerificationTokenEntity;
import com.cloudstore.repository.CustomerRepository;
import com.cloudstore.repository.ShopRepository;
import com.cloudstore.repository.UserAuthenticationRepository;
import com.cloudstore.repository.VerificationTokenRepository;

@Service
public class VerifyRegistrationServiceImpl implements VerifyRegistrationServiceInterface {
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Autowired
	private UserAuthenticationRepository userAuthenticationRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Override
	public void saveVerificationTokenForUser(String token, UserAuthenticationEntity user) {
		VerificationTokenEntity verificationTokenEntity = new VerificationTokenEntity(user, token);
		verificationTokenRepository.save(verificationTokenEntity);
	}

	@Override
	public UserAuthenticationEntity validateVerificationToken(String token) {
		VerificationTokenEntity verificationTokenEntity = verificationTokenRepository.findByToken(token);
		if (verificationTokenEntity == null) {
			return null;
		}
		UserAuthenticationEntity user = verificationTokenEntity.getUser();
		if(user == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();

		if (verificationTokenEntity.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
			// TODO Provide option for regenerating verification token
			verificationTokenRepository.delete(verificationTokenEntity);
			return null;
		}
		EnableStatusEnum userEnableStatusEnum = user.getEnableStatus();
		if(userEnableStatusEnum == EnableStatusEnum.DISABLED || userEnableStatusEnum == EnableStatusEnum.USER_DISABLED) {
			user.setEnableStatus(EnableStatusEnum.ENABLED);
			userAuthenticationRepository.save(user);
			verificationTokenRepository.delete(verificationTokenEntity);
			return user;
		}
		else {
			return null;
		}
	}

	@Override
	public void saveToDB(UserAuthenticationEntity user) {
		if (user.getRole().equalsIgnoreCase("CUSTOMER")) {
			CustomerEntity customer = customerRepository.findByEmail(user.getEmail());
			if(customer == null) {
				CustomerEntity customerEntity = new CustomerEntity();
				customerEntity.setFullName(user.getFullName());
				customerEntity.setEmail(user.getEmail());
				customerEntity.setRole(user.getRole());
	
				customerRepository.save(customerEntity);
			}
			else {
				customer.setEnableStatus(EnableStatusEnum.ENABLED);
				customerRepository.save(customer);
			}
		} 
		else if (user.getRole().equalsIgnoreCase("SHOP")) {
			ShopEntity shop = shopRepository.findByEmail(user.getEmail());
			if(shop == null) {
				ShopEntity shopEntity = new ShopEntity();
				shopEntity.setFullName(user.getFullName());
				shopEntity.setEmail(user.getEmail());
				shopEntity.setRole(user.getRole());

				shopRepository.save(shopEntity);
			}
			else {
				shop.setEnableStatus(EnableStatusEnum.ENABLED);
				shopRepository.save(shop);
			}
			
		}
	}
}
