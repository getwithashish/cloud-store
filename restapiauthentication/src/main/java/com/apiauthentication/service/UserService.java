package com.apiauthentication.service;

import com.apiauthentication.entity.User;
import com.apiauthentication.model.UserLoginModel;
import com.apiauthentication.model.UserRegistrationModel;

public interface UserService {

	User registerUser(UserRegistrationModel userModel);

	void saveVerificationTokenForUser(String token, User user);

	User loginUser(UserLoginModel userLoginModel);

	String validateVerificationToken(String token);

	User findByEmail(String email);

}
