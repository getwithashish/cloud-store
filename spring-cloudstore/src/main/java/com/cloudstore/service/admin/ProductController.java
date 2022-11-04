package com.cloudstore.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudstore.entity.ProductCategoryEntity;
import com.cloudstore.entity.ProductEntity;
import com.cloudstore.model.ProductModel;
import com.cloudstore.service.ShopServiceInterface;

@RestController
public class ProductController {
	
	@Autowired
	private ShopServiceInterface shopService;
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/products")
	public List<ProductEntity> listProducts() {
		List<ProductEntity> products = shopService.findAllProducts();
		return products;
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/productsbypin")
	public List<ProductEntity> listProductsByPincode(@RequestParam String pincode){
		List<ProductEntity> products = shopService.findAllProductsByPincode(pincode);
		return products;
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/product")
	public ProductEntity listProducts(@RequestParam String prodId) {
		ProductEntity product = shopService.findProductById(prodId);
		return product;
	}
	
//	@CrossOrigin("http://localhost:3000")
//	@GetMapping("/product")
//	public List<ProductEntity> listProductsByPincode(@RequestParam String pincode){
//		List<ProductEntity> products = shopService.findAllProductsByPincode(pincode);
//		return products;
//	}
	
	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("/product")
	public String deleteProduct(@RequestParam String prodId) {
		shopService.deleteProduct(prodId);
		return "SUCCESSFULLY DELETED";
	}
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/admin/product")
	public ProductEntity addProduct(@RequestBody ProductModel productModel) {
		ProductEntity product = shopService.addProduct(productModel);
		return product;
	}
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/admin/product/category")
	public ProductCategoryEntity addCategory(@RequestParam String category) {
		ProductCategoryEntity savedCategory = shopService.addCategory(category);
		return savedCategory;
	}

}
