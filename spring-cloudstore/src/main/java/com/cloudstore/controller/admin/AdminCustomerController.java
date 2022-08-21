package com.cloudstore.controller.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudstore.entity.CustomerEntity;
import com.cloudstore.model.DeleteModel;
import com.cloudstore.service.admin.AdminCustomerServiceInterface;


@RestController
public class AdminCustomerController {

	// TODO Only admin should be able to do these. Maybe use a separate collection
	// for admin?

	@Autowired
	private AdminCustomerServiceInterface adminCustomerService;

	@CrossOrigin("http://localhost:3000")
	@GetMapping("/admin/customers")
	public List<CustomerEntity> customerInfo() {
		List<CustomerEntity> customers = adminCustomerService.findAllCustomers();
		return customers;
	}

	protected Object addCustomerUser() {
		return null;
	}

	protected Object editCustomerUser() {
		return null;
	}

	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("/admin/customers")
	public String disableCustomers(@RequestBody DeleteModel deleteModel) {
		String emails[] = deleteModel.getEmails();
		adminCustomerService.disableCustomers(emails);

		return "SUCCESSFULLY DISABLED: \n" + emails;
	}

}
