package com.cloudstore.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cloudstore.entity.CustomerEntity;


public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {

	@Query("{email: ?0}")
	CustomerEntity findByEmail(String email);

	@Query("{email: {$in: ?0}}")
	List<CustomerEntity> findAllByEmails(String[] emails);

}
