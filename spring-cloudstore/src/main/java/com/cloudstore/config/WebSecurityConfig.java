package com.cloudstore.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cloudstore.filter.JWTFilter;

//TODO Put a limitation for the number of users that can be added or deleted or so on, in a single request.
//		Also, include pagination for responses.


@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private JWTFilter jwtFilter;

//	private static final String[] WHITE_LIST_URLS = { "/user/register", "/user/login", "/user/verifyRegistration",
//			"/user/customer", "/user/shop", "/admin/customers", "/admin/shops" };
	
	private static final String[] WHITE_LIST_URLS = { "/user/register", "/user/login", "/user/verifyRegistration",
			"/user/customer", "/user/shop", "/user/shop/product/category", "/products", "/product"};

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
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				.antMatchers("/user/customer/**").hasAuthority("CUSTOMER")
				.antMatchers("/user/shop/**").hasAuthority("SHOP")
				.anyRequest()
				.authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}
}
