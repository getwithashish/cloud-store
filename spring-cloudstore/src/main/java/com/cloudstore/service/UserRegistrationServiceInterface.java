package com.cloudstore.service;


import com.cloudstore.entity.UserAuthenticationEntity;
import com.cloudstore.model.UserRegistrationModel;


public interface UserRegistrationServiceInterface {

	UserAuthenticationEntity registerUser(UserRegistrationModel userRegistrationModel);

	void saveVerificationTokenForUser(String token, UserAuthenticationEntity user);

	String validateVerificationToken(String token);

}
