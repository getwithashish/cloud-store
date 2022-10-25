package com.cloudstore.event;


import org.springframework.context.ApplicationEvent;

import com.cloudstore.entity.UserAuthenticationEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {

	private UserAuthenticationEntity user;
	private String applicationUrl;

	public RegistrationCompleteEvent(UserAuthenticationEntity user, String applicationUrl) {
		super(user);
		this.user = user;
		this.applicationUrl = applicationUrl;
	}
}
