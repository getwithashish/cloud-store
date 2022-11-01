package com.cloudstore.controller.authentication;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

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
	private void verifyRegistration(@RequestParam("token") String token, HttpServletResponse response) throws IOException {
		UserAuthenticationEntity user = verifyRegistrationService.validateVerificationToken(token);
		if (user != null) {
			verifyRegistrationService.saveToDB(user);
//			return "User Verification Successful. Account is now enabled.";
			response.sendRedirect("http://localhost:3000/login");
		} else {
//			return "User Verification failed";
			response.sendRedirect("http://localhost:3000/verification/failure");
		}
	}

}
