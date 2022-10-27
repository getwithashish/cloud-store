package com.cloudstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cloudstore.entity.ProductCategoryEntity;

public interface CategoryRepository extends MongoRepository<ProductCategoryEntity, String> {
	
	

}
