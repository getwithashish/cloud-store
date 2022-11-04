package com.cloudstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cloudstore.entity.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {

}
