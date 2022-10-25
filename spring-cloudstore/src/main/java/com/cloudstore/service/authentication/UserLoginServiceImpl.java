package com.cloudstore.service.authentication;


import java.util.List;

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

	@Override
	public void disableUser(String email) {
		UserAuthenticationEntity user = userAuthenticationRepository.findByEmail(email);
		user.setEnabled(false);
		userAuthenticationRepository.save(user);
	}

	@Override
	public void disableUsers(String[] emails) {
		List<UserAuthenticationEntity> users = userAuthenticationRepository.findAllByEmails(emails);
		for (UserAuthenticationEntity user : users) {
			user.setEnabled(false);
		}
		userAuthenticationRepository.saveAll(users);
	}

	@Override
	public void enableUsers(String[] emails) {
		List<UserAuthenticationEntity> users = userAuthenticationRepository.findAllByEmails(emails);
		for (UserAuthenticationEntity user : users) {
			user.setEnabled(true);
		}
		userAuthenticationRepository.saveAll(users);
		
	}


}
