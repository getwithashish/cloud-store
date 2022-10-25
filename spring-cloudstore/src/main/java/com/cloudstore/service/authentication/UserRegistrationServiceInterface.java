package com.cloudstore.service.authentication;


import com.cloudstore.entity.UserAuthenticationEntity;
import com.cloudstore.model.UserRegistrationModel;


public interface UserRegistrationServiceInterface {

	UserAuthenticationEntity registerUser(UserRegistrationModel userRegistrationModel);
}
