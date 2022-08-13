package com.apiauthentication.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.apiauthentication.entity.User;


public interface UserRepository extends MongoRepository<User, String> {
	
	@Query("{email: ?0}")
	User findByEmail(String email);

}
