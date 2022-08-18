package com.cloudstore.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cloudstore.entity.UserAuthenticationEntity;
import com.cloudstore.service.authentication.UserLoginServiceInterface;


@Component
public class CustomAuthenticationManager implements AuthenticationManager {

	@Autowired
	private UserLoginServiceInterface userLoginService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		String rawPassword = authentication.getCredentials().toString();
		UserAuthenticationEntity user = userLoginService.findByEmail(email);
		String password = user.getPassword();
		if (user != null) {
			if (passwordEncoder.matches(rawPassword, password)) {
				return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
						authentication.getCredentials());
			}
		} else {
			throw new BadCredentialsException("Credentials is wrong");
			// TODO Debug this
		}
		return null;
	}

}
