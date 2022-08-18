package com.cloudstore.service;


import com.cloudstore.entity.CustomerEntity;


public interface CustomerServiceInterface {

	CustomerEntity customerInfo(String email);

	CustomerEntity disableCustomer(String email);

}
