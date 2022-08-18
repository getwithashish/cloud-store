package com.cloudstore.filter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cloudstore.entity.UserAuthenticationEntity;
import com.cloudstore.service.authentication.UserLoginServiceInterface;
import com.cloudstore.utility.JWTUtility;


@Component
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private UserLoginServiceInterface userLoginService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// TODO Check whether the user is enabled or not. If the user account has been
		// disabled, then the user
		// should not be able to do any operations until enabled again.

		String authorization = request.getHeader("Authorization");
		String token = null;
		String username = null;

		if (authorization != null && authorization.startsWith("Bearer")) {
			token = authorization.substring(7);
			username = jwtUtility.getUsernameFromToken(token);
		} else {
			throw new ServletException("Authorization header not provided");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserAuthenticationEntity user = userLoginService.findByEmail(username);
			if (jwtUtility.validateToken(token, user)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						user.getEmail(), user.getPassword());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				throw new ServletException("Cannot validate token and user obtained from db");
			}
			filterChain.doFilter(request, response);
		}

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		Collection<String> excludeUrlPatterns = new ArrayList<>();
		excludeUrlPatterns.add("/user/register");
		excludeUrlPatterns.add("/user/login");
		excludeUrlPatterns.add("/user/verifyRegistration");

		AntPathMatcher pathMatcher = new AntPathMatcher();
		return excludeUrlPatterns.stream().anyMatch(p -> pathMatcher.match(p, request.getServletPath()));

	}

}
