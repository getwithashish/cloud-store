package com.cloudstore.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudstore.entity.CustomerEntity;
import com.cloudstore.entity.EnableStatusEnum;
import com.cloudstore.model.EditCustomerModel;
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
		customer.setEnableStatus(EnableStatusEnum.USER_DISABLED);
		customerRepository.save(customer);

		userLoginService.disableUser(email);

		return customer;

	}

	@Override
	public CustomerEntity editCustomer(EditCustomerModel editModel) {
		CustomerEntity customer = customerRepository.findByEmail(editModel.getEmail());
		customer.setMobile(editModel.getMobile());
		customer.setImageUrl(editModel.getImageUrl());
		customer.setHouseName(editModel.getHouseName());
		customer.setStreetName(editModel.getStreetName());
		customer.setCityName(editModel.getCityName());
		customer.setPincode(editModel.getPincode());
		
		customerRepository.save(customer);
		return customer;
	}

}
