package com.cloudstore.service.authentication;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudstore.entity.EnableStatusEnum;
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

	@Override
	public void disableUser(String email) {
		UserAuthenticationEntity user = userAuthenticationRepository.findByEmail(email);
		// By customer and shop
		user.setEnableStatus(EnableStatusEnum.USER_DISABLED);
		userAuthenticationRepository.save(user);
	}

	@Override
	public void disableUsers(String[] emails) {
		List<UserAuthenticationEntity> users = userAuthenticationRepository.findAllByEmails(emails);
		// By admin
		for (UserAuthenticationEntity user : users) {
			user.setEnableStatus(EnableStatusEnum.ADMIN_DISABLED);
		}
		userAuthenticationRepository.saveAll(users);
	}

	@Override
	public void enableUsers(String[] emails) {
		List<UserAuthenticationEntity> users = userAuthenticationRepository.findAllByEmails(emails);
		for (UserAuthenticationEntity user : users) {
			user.setEnableStatus(EnableStatusEnum.ENABLED);
		}
		userAuthenticationRepository.saveAll(users);
		
	}


}
