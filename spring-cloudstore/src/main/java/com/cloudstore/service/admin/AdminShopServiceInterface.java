package com.cloudstore.service.admin;


import java.util.List;

import com.cloudstore.entity.ShopEntity;
import com.cloudstore.model.EditShopModel;


public interface AdminShopServiceInterface {

	List<ShopEntity> findAllShops();

	void disableShops(String deleteModel);

	void enableShops(String enableModel);

	ShopEntity editShops(EditShopModel editModel);

}
