package com.cloudstore.controller.admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudstore.entity.CustomerEntity;
import com.cloudstore.entity.ProductEntity;
import com.cloudstore.entity.ShopEntity;
import com.cloudstore.model.DeleteModel;
import com.cloudstore.model.EditCustomerModel;
import com.cloudstore.model.EditShopModel;
import com.cloudstore.model.EnableModel;
import com.cloudstore.model.ProductModel;
import com.cloudstore.service.admin.AdminShopServiceInterface;


@RestController
public class AdminShopController {

	@Autowired
	private AdminShopServiceInterface adminShopService;

	@CrossOrigin("http://localhost:3000")
	@GetMapping("/admin/shops")
	public List<ShopEntity> shopInfo() {
		List<ShopEntity> shops = adminShopService.findAllShops();
		return shops;
	}

	protected Object addShopUser() {
		return null;
	}

	@CrossOrigin("http://localhost:3000")
	@PutMapping("/admin/shops")
	protected ShopEntity editShops(@RequestBody EditShopModel editModel) {
		ShopEntity shop = adminShopService.editShops(editModel);
		return shop;
	}

	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("/admin/shops")
//	public String disableShops(@RequestBody DeleteModel deleteModel) {
	public String disableShops(@RequestParam String deleteModel) {
//		String[] emails = deleteModel.getEmails();
		adminShopService.disableShops(deleteModel);

		return "SUCCESSFULLY DISABLED: \n" + deleteModel;
	}
	
	@CrossOrigin("http://localhost:3000")
	@PatchMapping("/admin/shops")
	public String enableShops(@RequestParam String enableModel) {
//		String[] emails = enableModel.getEmails();
		adminShopService.enableShops(enableModel);

		return "SUCCESSFULLY ENABLED: \n" + enableModel;
	}

}
