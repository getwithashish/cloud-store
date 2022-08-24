package com.cloudstore.service.admin;


import java.util.List;

import com.cloudstore.entity.CustomerEntity;
import com.cloudstore.model.EditModel;


public interface AdminCustomerServiceInterface {

	List<CustomerEntity> findAllCustomers();

	void disableCustomers(String[] emails);

	void enableCustomers(String[] emails);

	CustomerEntity editCustomers(EditModel editModel);

}
