package com.apiauthentication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.apiauthentication.entity.VerificationToken;


public interface VerificationTokenRepository extends MongoRepository<VerificationToken, String> {

	VerificationToken findByToken(String token);

}
