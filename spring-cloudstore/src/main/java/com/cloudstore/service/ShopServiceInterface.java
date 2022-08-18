package com.cloudstore.service;


import com.cloudstore.entity.ShopEntity;


public interface ShopServiceInterface {

	public ShopEntity shopInfo(String email);

	public ShopEntity disableShop(String email);

}
