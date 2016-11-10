package org.ndshop.shop.service;

import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.shop.dto.ShopDto;
import org.ndshop.shop.entity.Shop;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by ike on 16-11-10.
 */
@Cacheable(value = "serviceCache")
public interface IShopSer extends IService<Shop,ShopDto> {

    default Shop findByName(String shopName){
        return null;
    }

    default void addShop(Shop shop){
        return;
    }
}
