package org.ndshop.shop.dao;

import org.ndshop.dbs.jpa.dao.MyRep;
import org.ndshop.shop.dto.ShopDto;
import org.ndshop.shop.entity.Shop;
import org.ndshop.user.common.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.Optional;
import java.util.Set;

/**
 * @Author: [caixianyong]
 * @Date: [2016-11-23 16:51]
 * @Description: [店铺dao]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IShopRep extends MyRep<Shop,ShopDto>{

    /*@Caching(cacheable = {
            @Cacheable(value = "shopDaoCache",key = "#name",condition = "#name!=null")
    })*/
    Shop findByName(String name);

    /*@Caching(cacheable = {
            @Cacheable(value = "shopDaoCache")
    })*/
    Set<Shop> findByUser(User user);

}
