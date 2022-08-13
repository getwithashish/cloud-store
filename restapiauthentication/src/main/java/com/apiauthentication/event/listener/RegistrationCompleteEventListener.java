package com.apiauthentication.event.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.apiauthentication.entity.User;
import com.apiauthentication.event.RegistrationCompleteEvent;
import com.apiauthentication.service.UserService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
	
	@Autowired
	private UserService userService;

	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
		// Create Verification token
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		userService.saveVerificationTokenForUser(token, user);
		
		// Send mail to user
		String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;
		
		log.info("Click the link to verify your account: {}", url);
	}
	

}
