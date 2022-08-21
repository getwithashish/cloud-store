package com.cloudstore.service.authentication;

import com.cloudstore.entity.UserAuthenticationEntity;

public interface VerifyRegistrationServiceInterface {

	void saveVerificationTokenForUser(String token, UserAuthenticationEntity user);

	UserAuthenticationEntity validateVerificationToken(String token);

	void saveToDB(UserAuthenticationEntity user);

}
