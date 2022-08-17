package com.cloudstore.service.authentication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudstore.entity.UserAuthenticationEntity;
import com.cloudstore.repository.UserAuthenticationRepository;


@Service
public class UserLoginServiceImpl implements UserLoginServiceInterface {

	@Autowired
	private UserAuthenticationRepository userAuthenticationRepository;

	@Override
	public UserAuthenticationEntity findByEmail(String email) {
		UserAuthenticationEntity user = userAuthenticationRepository.findByEmail(email);
		return user;
	}

}
