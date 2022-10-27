package com.cloudstore.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudstore.entity.EnableStatusEnum;
import com.cloudstore.entity.ProductCategoryEntity;
import com.cloudstore.entity.ProductEntity;
import com.cloudstore.entity.ShopEntity;
import com.cloudstore.model.EditShopModel;
import com.cloudstore.model.ProductModel;
import com.cloudstore.repository.CategoryRepository;
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
	private CategoryRepository categoryRepository;

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
		if(shop != null) {
			shop.setEnableStatus(EnableStatusEnum.USER_DISABLED);
			shopRepository.save(shop);

			userLoginService.disableUser(email);
			return shop;
		}
		else {
			return null;
		}
	}

	@Override
	public ProductEntity addProduct(ProductModel productModel) {
		ProductEntity product = new ProductEntity();
		
		product.setShopId(productModel.getShopId());
//		product.setLatString(productModel.getLatString());
//		product.setLongString(productModel.getLongString());
//		
//		product.setProdName(productModel.getProdName());
//		product.setImage(productModel.getImage());
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

	@Override
	public ProductEntity findProductById(String prodId) {
		Optional<ProductEntity> product = productRepository.findById(prodId);
		return product.get();
	}

	@Override
	public ShopEntity editShop(EditShopModel editModel) {
		ShopEntity shop = shopRepository.findByEmail(editModel.getEmail());
		shop.setOwnerFullName(editModel.getOwnerFullName());
		shop.setMobile(editModel.getMobile());
		shop.setImageUrl(editModel.getImageUrl());
		shop.setDocumentUrl(editModel.getDocumentUrl());
		shop.setStreetName(editModel.getStreetName());
		shop.setCityName(editModel.getCityName());
		shop.setPincode(editModel.getPincode());
		
		shop = shopRepository.save(shop);
		return shop;
	}

	@Override
	public ProductCategoryEntity addCategory(String category) {
		ProductCategoryEntity productCategory = new ProductCategoryEntity();
		productCategory.setCategory(category);
		
		ProductCategoryEntity savedCategory = categoryRepository.save(productCategory);
		return savedCategory;
	}

}
