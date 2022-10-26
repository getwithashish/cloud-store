package com.cloudstore.controller.authentication;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudstore.entity.EnableStatusEnum;
import com.cloudstore.entity.UserAuthenticationEntity;
import com.cloudstore.model.UserLoginModel;
import com.cloudstore.service.authentication.UserLoginServiceInterface;
import com.cloudstore.utility.JWTUtility;


@CrossOrigin("http://localhost:3000")
@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserLoginServiceInterface userLoginService;

	@Autowired
	private JWTUtility jwtUtility;

	@CrossOrigin("http://localhost:3000")
	@PostMapping("/user/login")
	public String loginUser(@RequestBody UserLoginModel userLoginModel) throws Exception {

		// TODO Restructure this code a bit.

		Authentication authenticatedUsernamePasswordAuthenticationToken = null;
		String email = userLoginModel.getEmail();
		String password = userLoginModel.getPassword();
		Authentication usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, password);
		try {
			authenticatedUsernamePasswordAuthenticationToken = 
					authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID CREDENTIALS ", e);
		}

//		final UserAuthenticationEntity user = userLoginService.findByEmail(email);
		final UserAuthenticationEntity user = (UserAuthenticationEntity)authenticatedUsernamePasswordAuthenticationToken.getPrincipal();
		EnableStatusEnum userEnableStatus = user.getEnableStatus();
		if(userEnableStatus == EnableStatusEnum.ENABLED) {
			final String token = jwtUtility.generateToken(user);
			return token;
		}
		else if(userEnableStatus == EnableStatusEnum.DISABLED) {
			String messageString = "D";
			return messageString;
		}
		else if(userEnableStatus == EnableStatusEnum.USER_DISABLED) {
			String messageString = "UD";
			return messageString;
		}
		else if(userEnableStatus == EnableStatusEnum.ADMIN_DISABLED) {
			String messageString = "AD";
			return messageString;
		}
		else {
			String messageString = null;
			return messageString;
		}
		
		// TODO Check whether enabled or disabled
	}
}
