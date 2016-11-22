package org.ndshop.shop.dao;

import org.ndshop.dbs.jpa.dao.MyRep;
import org.ndshop.shop.dto.ShopDto;
import org.ndshop.shop.entity.Shop;
import org.ndshop.user.common.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.Set;

/**
 * Created by ike on 16-11-10.
 */
public interface IShopRep extends MyRep<Shop,ShopDto>{

    @Caching(cacheable = {
            @Cacheable(value = "shopDaoCache",key = "#name",condition = "#name!=null")
    })
    Shop findByName(String name);

    @Caching(cacheable = {
            @Cacheable(value = "shopDaoCache")
    })
    Set<Shop> findByOwner(User owner);

}
