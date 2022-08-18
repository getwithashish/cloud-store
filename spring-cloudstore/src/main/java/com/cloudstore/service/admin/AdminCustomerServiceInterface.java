package com.cloudstore.service.admin;


import java.util.List;

import com.cloudstore.entity.CustomerEntity;


public interface AdminCustomerServiceInterface {

	List<CustomerEntity> findAllCustomers();

	void disableCustomers(String[] emails);

}
