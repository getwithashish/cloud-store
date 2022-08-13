package com.apiauthentication.service;

import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apiauthentication.entity.User;
import com.apiauthentication.entity.VerificationToken;
import com.apiauthentication.model.UserLoginModel;
import com.apiauthentication.model.UserRegistrationModel;
import com.apiauthentication.repository.UserRepository;
import com.apiauthentication.repository.VerificationTokenRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Override
	public User registerUser(UserRegistrationModel userModel) {
		User user = new User();
		user.setFullName(userModel.getFullName());
		user.setEmail(userModel.getEmail());
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		user.setRole("USER");
		
		userRepository.save(user);
		return user;
	}

	@Override
	public void saveVerificationTokenForUser(String token, User user) {
		VerificationToken verificationToken = new VerificationToken(user, token);
		verificationTokenRepository.save(verificationToken);
	}

	@Override
	public User loginUser(UserLoginModel userLoginModel) {
		String email = userLoginModel.getEmail();
		String password = userLoginModel.getPassword();
		
//		Optional<User> opt = userRepository.findByEmail(email);
//		User user = userRepository.findByEmail(email);
		
		User user = findByEmail(email);

		// What is the result when the query is unable to find the required value
		// It is an exception: java.util.NoSuchElementException
		
		if(user != null) {
			Boolean booleanStatus = checkPassword(password, user.getPassword());
			if(booleanStatus) {
				return user;
			}
			return null;
		}
		else {
			return null;
		}
		
	}

	private boolean checkPassword(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
		
	}

	@Override
	public String validateVerificationToken(String token) {
		VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
		if(verificationToken == null) {
			return "invalid";
		}
		User user = verificationToken.getUser();
		Calendar calendar = Calendar.getInstance();
		
		if(verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
			verificationTokenRepository.delete(verificationToken);
			return "expired";
		}
		
		user.setEnabled(true);
		userRepository.save(user);
		return "valid";
	}

	@Override
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

}
