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

import com.cloudstore.entity.UserAuthenticationEntity;
import com.cloudstore.model.UserLoginModel;
import com.cloudstore.service.authentication.UserLoginServiceInterface;
import com.cloudstore.utility.JWTUtility;


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

		String email = userLoginModel.getEmail();
		String password = userLoginModel.getPassword();
		Authentication usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, password);
		try {
			authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID CREDENTIALS ", e);
		}

		final UserAuthenticationEntity user = userLoginService.findByEmail(email);
		final String token = jwtUtility.generateToken(user);
		return token;
		// TODO Check whether enabled or disabled
	}
}
