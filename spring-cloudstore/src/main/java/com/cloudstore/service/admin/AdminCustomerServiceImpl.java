package com.cloudstore.service.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cloudstore.entity.CustomerEntity;
import com.cloudstore.repository.CustomerRepository;
import com.cloudstore.service.authentication.UserLoginServiceInterface;


public class AdminCustomerServiceImpl implements AdminCustomerServiceInterface {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserLoginServiceInterface userLoginService;

	@Override
	public List<CustomerEntity> findAllCustomers() {
		List<CustomerEntity> customers = customerRepository.findAll();
		return customers;
	}

	@Override
	public void disableCustomers(String[] emails) {
		List<CustomerEntity> customers = customerRepository.findAllByEmails(emails);
		for (CustomerEntity customer : customers) {
			customer.setEnabled(false);
		}
		customerRepository.saveAll(customers);

		userLoginService.disableUsers(emails);

	}

}
