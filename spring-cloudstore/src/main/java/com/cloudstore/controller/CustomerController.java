package com.cloudstore.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudstore.utility.JWTUtility;


@RestController
public class CustomerController extends AbstractUserController {
	
	@Autowired
	private JWTUtility jwtUtility;

	@GetMapping("/user/list")
	@Override
	protected ArrayList<String> listUser(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		String token = authorizationHeader.substring(7);
		String email = jwtUtility.getUsernameFromToken(token);
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object addUser() {
		// TODO Auto-generated method stub
		return super.addUser();
	}

//	@Override
//	protected Object editUser() {
//		// TODO Auto-generated method stub
//		return super.editUser();
//	}

	@Override
	protected Object deleteUser() {
		// TODO Auto-generated method stub
		return super.deleteUser();
	}

}
