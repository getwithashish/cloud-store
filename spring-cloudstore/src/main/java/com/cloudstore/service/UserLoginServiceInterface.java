package com.cloudstore.service;


import com.cloudstore.entity.UserAuthenticationEntity;


public interface UserLoginServiceInterface {

	UserAuthenticationEntity findByEmail(String email);

}
