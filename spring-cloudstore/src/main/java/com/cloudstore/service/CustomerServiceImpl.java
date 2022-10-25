package com.cloudstore.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudstore.entity.CustomerEntity;
import com.cloudstore.repository.CustomerRepository;
import com.cloudstore.service.authentication.UserLoginServiceInterface;


@Service
public class CustomerServiceImpl implements CustomerServiceInterface {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserLoginServiceInterface userLoginService;

	@Override
	public CustomerEntity customerInfo(String email) {
		CustomerEntity customer = customerRepository.findByEmail(email);
		return customer;
	}

	@Override
	public CustomerEntity disableCustomer(String email) {
		CustomerEntity customer = customerRepository.findByEmail(email);
		customer.setEnabled(false);
		customerRepository.save(customer);

		userLoginService.disableUser(email);

		return customer;

	}

}
