package com.cloudstore.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudstore.entity.UserAuthenticationEntity;
import com.cloudstore.service.authentication.UserRegistrationServiceInterface;
import com.cloudstore.service.authentication.VerifyRegistrationServiceInterface;

@RestController
public class VerifyRegistrationController {
	
	@Autowired
	private VerifyRegistrationServiceInterface verifyRegistrationService;
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/user/verifyRegistration")
	private String verifyRegistration(@RequestParam("token") String token) {
		UserAuthenticationEntity user = verifyRegistrationService.validateVerificationToken(token);
		if (user != null) {
			verifyRegistrationService.saveToDB(user);
			return "User Verification Successful";
		} else {
			return "User Verification failed";
		}
	}

}
