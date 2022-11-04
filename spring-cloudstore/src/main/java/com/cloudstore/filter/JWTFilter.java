package com.cloudstore.filter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cloudstore.entity.UserAuthenticationEntity;
import com.cloudstore.service.authentication.UserLoginServiceInterface;
import com.cloudstore.utility.JWTUtility;

import io.jsonwebtoken.Claims;
import lombok.experimental.var;


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
				
				Claims body = jwtUtility.getBody(token);
				List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
				Set<SimpleGrantedAuthority> simpleGrantedAuthorities =
						authorities.stream()
						.map(m -> new SimpleGrantedAuthority(m.get("authority")))
						.collect(Collectors.toSet());
				
				Authentication usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(user.getEmail(), 
								null,
								simpleGrantedAuthorities);
				
				//This was used when UsernamePasswordAuthentication was used instead of Authentication
//				usernamePasswordAuthenticationToken
//						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
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
		excludeUrlPatterns.add("/products");
		excludeUrlPatterns.add("/product");
		excludeUrlPatterns.add("/user/shop/product/category");
		excludeUrlPatterns.add("/user/shops");

		AntPathMatcher pathMatcher = new AntPathMatcher();
		return excludeUrlPatterns.stream().anyMatch(p -> pathMatcher.match(p, request.getServletPath()));

	}

}
