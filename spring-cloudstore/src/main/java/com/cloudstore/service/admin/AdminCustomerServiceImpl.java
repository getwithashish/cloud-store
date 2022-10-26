package com.cloudstore.service.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cloudstore.entity.CustomerEntity;
import com.cloudstore.entity.EnableStatusEnum;
import com.cloudstore.entity.UserAuthenticationEntity;
import com.cloudstore.model.EditModel;
import com.cloudstore.repository.CustomerRepository;
import com.cloudstore.repository.UserAuthenticationRepository;
import com.cloudstore.service.authentication.UserLoginServiceInterface;


@Service
public class AdminCustomerServiceImpl implements AdminCustomerServiceInterface {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserLoginServiceInterface userLoginService;
	
	@Autowired
	private UserAuthenticationRepository userAuthenticationRepository;

	@Override
	public List<CustomerEntity> findAllCustomers() {
		List<CustomerEntity> customers = customerRepository.findAll();
		return customers;
	}

	@Override
	public void disableCustomers(String[] emails) {
		List<CustomerEntity> customers = customerRepository.findAllByEmails(emails);
		for (CustomerEntity customer : customers) {
			customer.setEnableStatus(EnableStatusEnum.ADMIN_DISABLED);;
		}
		customerRepository.saveAll(customers);

		userLoginService.disableUsers(emails);

	}

	@Override
	public void enableCustomers(String[] emails) {
		List<CustomerEntity> customers = customerRepository.findAllByEmails(emails);
		for (CustomerEntity customer : customers) {
			customer.setEnableStatus(EnableStatusEnum.ENABLED);;
		}
		customerRepository.saveAll(customers);

		userLoginService.enableUsers(emails);
		
	}

	@Override
	public CustomerEntity editCustomers(EditModel editModel) {
		CustomerEntity customer = customerRepository.findByEmail(editModel.getEmail());
		if(customer != null) {
			customer.setFullName(editModel.getFullName());
			customer.setRole(editModel.getRole());
			customer.setHouseName(editModel.getHouseName());
			customer.setStreetName(editModel.getStreetName());
			customer.setCityName(editModel.getCityName());
			customer.setStateName(editModel.getStateName());
			
			customerRepository.save(customer);
			
			UserAuthenticationEntity user = userAuthenticationRepository.findByEmail(editModel.getEmail());
			user.setFullName(editModel.getFullName());
			user.setRole(editModel.getRole());
			
			userAuthenticationRepository.save(user);
		}
		else {
			throw new UsernameNotFoundException("Customer with that email does not exist");
		}
		return customer;
		
	}

}
