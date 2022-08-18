package com.cloudstore.service.admin;


import java.util.List;

import com.cloudstore.entity.ShopEntity;


public interface AdminShopServiceInterface {

	List<ShopEntity> findAllShops();

	void disableShops(String[] emails);

}
