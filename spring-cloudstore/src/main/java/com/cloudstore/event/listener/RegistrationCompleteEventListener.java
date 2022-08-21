package com.cloudstore.event.listener;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.cloudstore.entity.UserAuthenticationEntity;
import com.cloudstore.event.RegistrationCompleteEvent;
import com.cloudstore.service.authentication.UserRegistrationServiceInterface;
import com.cloudstore.service.authentication.VerifyRegistrationServiceInterface;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

	@Autowired
	private VerifyRegistrationServiceInterface verifyRegistrationService;

	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
		UserAuthenticationEntity user = event.getUser();
		String token = UUID.randomUUID().toString();
		verifyRegistrationService.saveVerificationTokenForUser(token, user);
		String url = event.getApplicationUrl() + "/user/verifyRegistration?token=" + token;
		log.info("Click the link to verify: {}", url);
	}

}
