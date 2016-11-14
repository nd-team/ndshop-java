package org.ndshop.shop.service;

import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.shop.dto.ShopDto;
import org.ndshop.shop.entity.Shop;
import org.ndshop.user.common.dto.UserDto;
import org.ndshop.user.common.entity.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.Set;

/**
 * Created by ike on 16-11-10.
 */
@Cacheable(value = "serviceCache")
public interface IShopSer extends IService<Shop, ShopDto> {

    default Shop findByName(String name) {
        return null;
    }

    default Set<Shop> findByOwnerName(String ownerName) {
        return null;
    }

    default void addShopByOwnerName(Shop shop, String ownerName) {
    }

}
