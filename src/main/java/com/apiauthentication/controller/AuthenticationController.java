package com.apiauthentication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apiauthentication.entity.User;
import com.apiauthentication.event.RegistrationCompleteEvent;
import com.apiauthentication.model.UserLoginModel;
import com.apiauthentication.model.UserRegistrationModel;
import com.apiauthentication.service.UserService;
import com.mongodb.client.model.ReturnDocument;

@RestController
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/register")
	public String registerUser(@Valid @RequestBody UserRegistrationModel userRegisterModel, final HttpServletRequest request, HttpServletResponse response) {
		User user = userService.registerUser(userRegisterModel);
		publisher.publishEvent(new RegistrationCompleteEvent(
				user,
				getApplicationUrl(request)));
//		response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//		response.addHeader("Access-Control-Allow-Origin", "*");
		return "SUCCESS";
	}
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/login")
	public String loginUser(@Valid @RequestBody UserLoginModel userLoginModel) {
		User user = userService.loginUser(userLoginModel);
		if(user != null) {
			return "SUCCESS";
		}
		else {
			return "FAILURE";
		}
	}
	
	@GetMapping("/verifyRegistration")
	public String verifyRegistration(@RequestParam("token") String token) {
		String verificationStatus = userService.validateVerificationToken(token);
		if(verificationStatus.equalsIgnoreCase("valid")) {
			return "User Verification Successful";
		}
		else {
			return "User Verification failed";
		}
	}

	private String getApplicationUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

}
