package com.cloudstore.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.cloudstore.entity.CustomerEntity;


public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {

}
