package com.cloudstore.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.cloudstore.entity.VerificationTokenEntity;


public interface VerificationTokenRepository extends MongoRepository<VerificationTokenEntity, String> {

	VerificationTokenEntity findByToken(String token);

}
