package com.cloudstore.controller.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudstore.entity.CustomerEntity;
import com.cloudstore.model.DeleteModel;
import com.cloudstore.model.EditCustomerModel;
import com.cloudstore.model.EnableModel;
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

	@CrossOrigin("http://localhost:3000")
	@PutMapping("/admin/customers")
	protected CustomerEntity editCustomers(@RequestBody EditCustomerModel editModel) {
		CustomerEntity customer = adminCustomerService.editCustomers(editModel);
		return customer;
	}

	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("/admin/customers")
//	public String disableCustomers(@RequestBody DeleteModel deleteModel) {
	public String disableCustomers(@RequestParam String deleteModel) {
//		String emails[] = deleteModel.getEmails();
		adminCustomerService.disableCustomers(deleteModel);

		return "SUCCESSFULLY DISABLED: \n" + deleteModel;
	}
	
	@CrossOrigin("http://localhost:3000")
	@PatchMapping("/admin/customers")
//	public String enableCustomers(@RequestBody EnableModel enableModel) {
	public String enableCustomers(@RequestParam String enableModel) {
//		String emails[] = enableModel.getEmails();
		adminCustomerService.enableCustomers(enableModel);

		return "SUCCESSFULLY DISABLED: \n" + enableModel;
	}

}
