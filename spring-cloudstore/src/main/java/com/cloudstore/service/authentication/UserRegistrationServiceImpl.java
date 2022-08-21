package com.cloudstore.service.authentication;


import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cloudstore.entity.CustomerEntity;
import com.cloudstore.entity.ShopEntity;
import com.cloudstore.entity.UserAuthenticationEntity;
import com.cloudstore.entity.VerificationTokenEntity;
import com.cloudstore.model.UserRegistrationModel;
import com.cloudstore.repository.CustomerRepository;
import com.cloudstore.repository.ShopRepository;
import com.cloudstore.repository.UserAuthenticationRepository;
import com.cloudstore.repository.VerificationTokenRepository;


@Service
public class UserRegistrationServiceImpl implements UserRegistrationServiceInterface {

	// TODO How to prevent multiple registration attack. How about using captcha
	// similar to that of binance

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserAuthenticationRepository userAuthenticationRepository;

	@Override
	public UserAuthenticationEntity registerUser(UserRegistrationModel userRegistrationModel) {
		UserAuthenticationEntity user = new UserAuthenticationEntity();
		user.setFullName(userRegistrationModel.getFullName());
		user.setEmail(userRegistrationModel.getEmail());
		user.setPassword(passwordEncoder.encode(userRegistrationModel.getPassword()));
		user.setRole(userRegistrationModel.getRole());

		userAuthenticationRepository.save(user);
		return user;
	}

	

}
