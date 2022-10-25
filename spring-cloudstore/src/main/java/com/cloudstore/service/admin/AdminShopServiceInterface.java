package com.cloudstore.service.admin;


import java.util.List;

import com.cloudstore.entity.ShopEntity;
import com.cloudstore.model.EditModel;


public interface AdminShopServiceInterface {

	List<ShopEntity> findAllShops();

	void disableShops(String[] emails);

	void enableShops(String[] emails);

	ShopEntity editCustomers(EditModel editModel);

}
