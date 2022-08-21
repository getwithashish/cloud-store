package com.cloudstore.controller.authentication;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudstore.entity.UserAuthenticationEntity;
import com.cloudstore.event.RegistrationCompleteEvent;
import com.cloudstore.model.UserRegistrationModel;
import com.cloudstore.service.authentication.UserRegistrationServiceInterface;


@CrossOrigin("http://localhost:3000")
@RestController
public class RegistrationController {

	@Autowired
	private UserRegistrationServiceInterface userRegistrationService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@CrossOrigin("http://localhost:3000")
	@PostMapping("/user/register")
	public String registerUser(@RequestBody UserRegistrationModel userRegistrationModel,
			final HttpServletRequest request) {
		UserAuthenticationEntity user = userRegistrationService.registerUser(userRegistrationModel);
		String applicationBaseUrl = getApplicationUrl(request);
		RegistrationCompleteEvent registrationCompleteEvent = new RegistrationCompleteEvent(user, applicationBaseUrl);
		publisher.publishEvent(registrationCompleteEvent);
		return "SUCCESS";
		// TODO Check whether user already exists
	}

	private String getApplicationUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
}
