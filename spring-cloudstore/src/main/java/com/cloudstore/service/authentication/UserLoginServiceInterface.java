package com.cloudstore.service.authentication;


import com.cloudstore.entity.UserAuthenticationEntity;


public interface UserLoginServiceInterface {

	UserAuthenticationEntity findByEmail(String email);

	void disableUser(String email);

	void disableUsers(String emails);

	void enableUsers(String emails);

}
