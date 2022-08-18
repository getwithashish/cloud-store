package com.cloudstore.utility;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;


@Component
public class JWTExtractor {

	public String extractTokenFromHeader(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		String token = authorizationHeader.substring(7);
		return token;
	}

}
