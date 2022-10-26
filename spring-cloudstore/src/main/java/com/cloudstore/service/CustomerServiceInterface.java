package com.cloudstore.service;


import com.cloudstore.entity.CustomerEntity;
import com.cloudstore.model.EditCustomerModel;


public interface CustomerServiceInterface {

	CustomerEntity customerInfo(String email);

	CustomerEntity disableCustomer(String email);

	CustomerEntity editCustomer(EditCustomerModel editModel);

}
