package com.cloudstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cloudstore.entity.ProductEntity;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {

	@Query("{prodName: {$in: ?0}}")
	List<ProductEntity> findAllByNames(String[] prodNames);
	
	@Query("{prodName: ?0}")
	Optional<ProductEntity> findByName(String prodName);
	
//	@Query("{prodName: /.*?0.*/i}")
	@Query("{prodName: {$regex: /?0/, $options: i}}")
	List<ProductEntity> findSimilarProducts(String prodName);
	
//	@Query("{pincode: {}}")
//	List<ProductEntity> findProductByPincode(String pincode);

}
