package com.cloudstore.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cloudstore.entity.UserAuthenticationEntity;


public interface UserAuthenticationRepository extends MongoRepository<UserAuthenticationEntity, String> {

	@Query("{email: ?0}")
	UserAuthenticationEntity findByEmail(String email);

}
