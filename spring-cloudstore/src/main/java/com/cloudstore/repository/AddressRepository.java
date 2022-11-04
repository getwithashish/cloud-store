package com.cloudstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cloudstore.entity.AddressEntity;

public interface AddressRepository extends MongoRepository<AddressEntity, String> {

}
