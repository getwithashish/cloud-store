package com.cloudstore.service;


import java.util.List;

import com.cloudstore.entity.ProductCategoryEntity;
import com.cloudstore.entity.ProductEntity;
import com.cloudstore.entity.ShopEntity;
import com.cloudstore.model.EditShopModel;
import com.cloudstore.model.ProductModel;


public interface ShopServiceInterface {

	public ShopEntity shopInfo(String email);

	public ShopEntity disableShop(String email);

	public ProductEntity addProduct(ProductModel productModel);

	public List<ProductEntity> findAllProducts();

	public void disableProducts(String[] prodNames);

	public ProductEntity findProductById(String prodId);

	public ShopEntity editShop(EditShopModel editModel);

	public ProductCategoryEntity addCategory(String category);

	public List<ProductCategoryEntity> viewCategories();

	ProductEntity findProductByName(String prodName);

	public List<ProductEntity> findProductsByShop(ShopEntity shop);

}
