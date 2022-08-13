package com.apiauthentication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
//import com.apiauthentication.utility.JWTUtility;
import com.mongodb.client.model.ReturnDocument;

@RestController
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
//	@Autowired
//	private JWTUtility jwtUtility;
	
//	@Autowired
//	private AuthenticationManager authenticationManager;
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/register")
	public String registerUser(@Valid @RequestBody UserRegistrationModel userRegisterModel, final HttpServletRequest request, HttpServletResponse response) {
		User user = userService.registerUser(userRegisterModel);
		publisher.publishEvent(new RegistrationCompleteEvent(
				user,
				getApplicationUrl(request)));
		return "SUCCESS";
	}
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/login")
	public String loginUser(@Valid @RequestBody UserLoginModel userLoginModel) throws Exception {
		
//		String email = userLoginModel.getEmail();
//		String password = userLoginModel.getPassword();
//		Authentication usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//				email, password);
//		try {
//			authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//		}
//		catch (BadCredentialsException e) {
//			throw new Exception("INVALID CREDENTIALS ", e);
//		}
//		
//		final User user = userService.findByEmail(email);
//		final String token = jwtUtility.generateToken(user);
		
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
