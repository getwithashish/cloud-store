package com.cloudstore.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.cloudstore.entity.ShopEntity;


public interface ShopRepository extends MongoRepository<ShopEntity, String> {

}
