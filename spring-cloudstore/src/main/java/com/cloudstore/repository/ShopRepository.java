package com.cloudstore.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cloudstore.entity.ShopEntity;


public interface ShopRepository extends MongoRepository<ShopEntity, String> {

	@Query("{email: ?0}")
	ShopEntity findByEmail(String email);

	@Query()
	List<ShopEntity> findAllByEmails(String[] emails);

}
