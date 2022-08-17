package com.cloudstore.service;


import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cloudstore.entity.UserAuthenticationEntity;
import com.cloudstore.entity.VerificationTokenEntity;
import com.cloudstore.model.UserRegistrationModel;
import com.cloudstore.repository.UserAuthenticationRepository;
import com.cloudstore.repository.VerificationTokenRepository;


@Service
public class UserRegistrationServiceImpl implements UserRegistrationServiceInterface {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserAuthenticationRepository userAuthenticationRepository;

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Override
	public UserAuthenticationEntity registerUser(UserRegistrationModel userRegistrationModel) {
		UserAuthenticationEntity user = new UserAuthenticationEntity();
		user.setFullName(userRegistrationModel.getFullName());
		user.setEmail(userRegistrationModel.getEmail());
		user.setPassword(passwordEncoder.encode(userRegistrationModel.getPassword()));
		user.setRole("");

		userAuthenticationRepository.save(user);
		return user;
	}

	@Override
	public void saveVerificationTokenForUser(String token, UserAuthenticationEntity user) {
		VerificationTokenEntity verificationTokenEntity = new VerificationTokenEntity(user, token);
		verificationTokenRepository.save(verificationTokenEntity);
	}

	@Override
	public String validateVerificationToken(String token) {
		VerificationTokenEntity verificationTokenEntity = verificationTokenRepository.findByToken(token);
		if (verificationTokenEntity == null) {
			return "invalid";
		}
		UserAuthenticationEntity user = verificationTokenEntity.getUser();
		Calendar calendar = Calendar.getInstance();

		if (verificationTokenEntity.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
			// TODO Provide option for regenerating verification token
			verificationTokenRepository.delete(verificationTokenEntity);
			return "expired";
		}

		user.setEnabled(true);
		userAuthenticationRepository.save(user);
		return "valid";

	}

}
