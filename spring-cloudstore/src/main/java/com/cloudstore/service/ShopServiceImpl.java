package com.cloudstore.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cloudstore.Model.ProductStockUpdateModel;
import com.cloudstore.entity.CustomerEntity;
import com.cloudstore.entity.EnableStatusEnum;
import com.cloudstore.entity.OrderEntity;
import com.cloudstore.entity.OrderProductList;
import com.cloudstore.entity.ProductCategoryEntity;
import com.cloudstore.entity.ProductEntity;
import com.cloudstore.entity.ProductShopEntryEntity;
import com.cloudstore.entity.ShopEntity;
import com.cloudstore.model.EditShopModel;
import com.cloudstore.model.OrderModel;
import com.cloudstore.model.OrderProductListModel;
import com.cloudstore.model.ProductModel;
import com.cloudstore.model.ShopIdsModel;
import com.cloudstore.repository.CategoryRepository;
import com.cloudstore.repository.OrderRepository;
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
	private OrderRepository orderRepository;

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
		ProductEntity product;
		
		Authentication usernamePasswordAuthenticationToken = 
				SecurityContextHolder.getContext().getAuthentication();
		String email = usernamePasswordAuthenticationToken.getName();
		ShopEntity shop = shopInfo(email);
		
		ProductShopEntryEntity productShop = new ProductShopEntryEntity();
		productShop.setShopId(shop.getId());
		productShop.setStock(productModel.getStock());
		
		ProductEntity productCheck = findProductByName(productModel.getProdName());
		if(productCheck != null) {
			productCheck.getShops().add(productShop);
			productCheck.getPincode().add(shop.getPincode());
			product = productCheck;
		}
		else {
			product = new ProductEntity();
			List<ProductShopEntryEntity> listProductShop = new ArrayList<ProductShopEntryEntity>();
			listProductShop.add(productShop);
			
			List<String> listPincode = new ArrayList<String>();
			listPincode.add(shop.getPincode());
			
			product.setShops(listProductShop);
			product.setPincode(listPincode);
			product.setProdName(productModel.getProdName());
			product.setImageUrl(productModel.getImageUrl());
			product.setCategory(productModel.getCategory());
			product.setMainUnit(productModel.getMainUnit());
			product.setSaleUnit(productModel.getSaleUnit());
			product.setWeight(productModel.getWeight());
			product.setPrice(productModel.getPrice());
			product.setIncrement(productModel.getIncrement());
		}

		product = productRepository.save(product);
		if(shop.getProductId() != null) {
			shop.getProductId().add(product.getId());
		}
		else {
			List<String> productIdList = new ArrayList<String>();
			productIdList.add(product.getId());
			shop.setProductId(productIdList);
		}
		shopRepository.save(shop);
		return product;
	}

	@Override
	public List<ProductEntity> findAllProducts() {
		List<ProductEntity> products = productRepository.findAll();
		return products;
	}

	@Override
	public void disableProducts(String prodNames) {
		Optional<ProductEntity> products = productRepository.findByName(prodNames);
//		for (ProductEntity product : products) {
//			product.setEnabled(false);
//			productRepository.delete(product);
//			}
//		productRepository.saveAll(products);
		productRepository.delete(products.get());		
	}

	@Override
	public ProductEntity findProductById(String prodId) {
		Optional<ProductEntity> product = productRepository.findById(prodId);
		return product.get();
	}
	
	@Override
	public ProductEntity findProductByName(String prodName) {
		Optional<ProductEntity> product = productRepository.findByName(prodName);
		if(product.isPresent()) {
			return product.get();
		}
		else {
			return null;
		}
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

	@Override
	public List<ProductCategoryEntity> viewCategories() {
		List<ProductCategoryEntity> categories = categoryRepository.findAll();
		return categories;
	}

	@Override
	public List<ProductEntity> findProductsByShop(ShopEntity shop) {
		List<String> productIdList = shop.getProductId();
		List<ProductEntity> products = (List<ProductEntity>) productRepository.findAllById(productIdList);
		return products;
	}

	@Override
	public ProductEntity updateStock(String shopEmail, ProductStockUpdateModel productStock) {
		ShopEntity shop = shopInfo(shopEmail);
		ProductEntity product = findProductById(productStock.getProdId());
		List<ProductShopEntryEntity> updatedShopStock = new ArrayList<ProductShopEntryEntity>();
		List<ProductShopEntryEntity> shopStock = product.getShops();
		for (ProductShopEntryEntity item : shopStock) {
			System.out.println("Item Shop Id: " + item.getShopId());
			System.out.println("Shop Id: " + shop.getId());
			if(item.getShopId().equals(shop.getId())) {
				item.setStock(productStock.getStock());
			}
			updatedShopStock.add(item);
		}
		product.setShops(updatedShopStock);
		
		System.out.println("Product Output: "+ product);

		ProductEntity updatedProduct = productRepository.save(product);
		return updatedProduct;
	}

	@Override
	public List<ProductEntity> similarProducts(String prodName) {
		List<ProductEntity> products = productRepository.findSimilarProducts(prodName);
		return products;
	}

	@Override
	public ProductEntity addSimilarProducts(String prodId) {
		Authentication usernamePasswordAuthenticationToken = 
				SecurityContextHolder.getContext().getAuthentication();
		String email = usernamePasswordAuthenticationToken.getName();
		ShopEntity shop = shopInfo(email);
		shop.getProductId().add(prodId);
		shopRepository.save(shop);
		
		ProductShopEntryEntity productShop = new ProductShopEntryEntity();
		productShop.setShopId(shop.getId());
		
		ProductEntity product = findProductById(prodId);
		product.getShops().add(productShop);
		productRepository.save(product);
		
		return product;
		
	}

	@Override
	public List<ShopEntity> getShopList(ShopIdsModel shopIds) {
		List<ShopEntity> shops = (List<ShopEntity>) shopRepository.findAllById(shopIds.getShopIds());
		return shops;
	}

	@Override
	public void deleteProduct(String prodId) {
		productRepository.deleteById(prodId);
	}

	@Override
	public List<ProductEntity> findAllProductsByPincode(String pincode) {
		List<ProductEntity> products = productRepository.findAllByPincode(pincode);
		return products;
	}

	@Override
	public String[] createOrder(CustomerEntity customer, OrderModel orderModel) {
		
		List<OrderProductListModel> orderProduct = orderModel.getOrderProductList();
		
		List<OrderProductList> orderedProducts = new ArrayList<OrderProductList>();
		double totalAmt = 0.00;
		
		for (OrderProductListModel item : orderProduct) {
			ProductEntity prod = findProductById(item.getProdId());
			OrderProductList odl = new OrderProductList();		
			odl.setProdId(prod.getId());
			odl.setShopId(item.getShopId());
			odl.setQuantity(item.getQuantity());
			odl.setPrice(prod.getPrice());
			orderedProducts.add(odl);
			
			totalAmt += (prod.getPrice() * item.getQuantity());
		}
		
		long time = System.currentTimeMillis();
		 
		 OrderEntity order = new OrderEntity();
		 order.setCustId(customer.getId());
		 order.setProducts(orderedProducts);
		 order.setTotalAmt(totalAmt);
		 order.setTime(time);
		 order.setAddressId(orderModel.getAddressId());		 
		 
		 OrderEntity savedOrder = orderRepository.save(order);
		 String[] orderIdAndAmount = {savedOrder.getId(), totalAmt + ""};
		 
		 return orderIdAndAmount;
	}

}
