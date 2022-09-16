package com.cloudstore.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudstore.entity.ProductEntity;
import com.cloudstore.entity.ShopEntity;
import com.cloudstore.model.ProductModel;
import com.cloudstore.repository.ProductRepository;
import com.cloudstore.repository.ShopRepository;
import com.cloudstore.service.authentication.UserLoginServiceInterface;


@Service
public class ShopServiceImpl implements ShopServiceInterface {

	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserLoginServiceInterface userLoginService;

	@Override
	public ShopEntity shopInfo(String email) {
		ShopEntity shop = shopRepository.findByEmail(email);
		return shop;
	}

	@Override
	public ShopEntity disableShop(String email) {
		ShopEntity shop = shopRepository.findByEmail(email);
		shop.setEnabled(false);
		shopRepository.save(shop);

		userLoginService.disableUser(email);

		return shop;
	}

	@Override
	public ProductEntity addProduct(ProductModel productModel) {
		ProductEntity product = new ProductEntity();
		product.setProdName(productModel.getProdName());
		product.setImage(productModel.getImage());
		product.setCategory(productModel.getCategory());
		product.setMainUnit(productModel.getMainUnit());
		product.setSaleUnit(productModel.getSaleUnit());
		product.setWeight(productModel.getWeight());
		product.setPrice(productModel.getPrice());
		product.setIncrement(productModel.getIncrement());
		
		productRepository.save(product);
		return product;
	}

	@Override
	public List<ProductEntity> findAllProducts() {
		List<ProductEntity> products = productRepository.findAll();
		return products;
	}

	@Override
	public void disableProducts(String[] prodNames) {
		List<ProductEntity> products = productRepository.findAllByNames(prodNames);
		for (ProductEntity product : products) {
			product.setEnabled(false);
			}
		productRepository.saveAll(products);
		
	}

}
