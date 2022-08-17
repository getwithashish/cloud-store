package com.cloudstore.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cloudstore.filter.JWTFilter;


@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private JWTFilter jwtFilter;

	private static final String[] WHITE_LIST_URLS = { 
			"/user/register", 
			"/user/login", 
			"/user/verifyRegistration" };

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.addFilterAt(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		http.cors()
		.and()
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.antMatchers(WHITE_LIST_URLS)
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}
}