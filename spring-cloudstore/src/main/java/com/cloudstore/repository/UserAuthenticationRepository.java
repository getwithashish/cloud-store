package com.cloudstore.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cloudstore.entity.UserAuthenticationEntity;


public interface UserAuthenticationRepository extends MongoRepository<UserAuthenticationEntity, String> {

	@Query("{email: ?0}")
	UserAuthenticationEntity findByEmail(String email);

	@Query()
	List<UserAuthenticationEntity> findAllByEmail(String[] emails);

}
