package com.apiauthentication.entity;

import java.util.Calendar;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
public class VerificationToken {
	
	private static final int EXPIRATION_TIME = 10; 
	
	@Id
	private String id;
	private String token;
	private Date expirationTime;
	
	private User user;
	
	public VerificationToken(User user, String token) {
		super();
		this.user = user;
		this.token = token;
		this.expirationTime = calculateExpirationTime(EXPIRATION_TIME);
	}
	
	public VerificationToken(String token) {
		super();
		this.token = token;
		this.expirationTime = calculateExpirationTime(EXPIRATION_TIME);
	}

	private Date calculateExpirationTime(int expirationTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.MINUTE, expirationTime);
		return new Date(calendar.getTime().getTime());
	}

}
