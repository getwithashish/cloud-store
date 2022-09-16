package com.cloudstore.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cloudstore.entity.ProductEntity;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {

	@Query("{prodName: {$in: ?0}}")
	List<ProductEntity> findAllByNames(String[] prodNames);

}
