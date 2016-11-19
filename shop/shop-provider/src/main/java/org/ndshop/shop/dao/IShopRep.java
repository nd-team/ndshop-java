package org.ndshop.shop.dao;

import org.ndshop.dbs.jpa.dao.MyRep;
import org.ndshop.shop.dto.ShopDto;
import org.ndshop.shop.entity.Shop;
import org.ndshop.user.common.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;
import java.util.Set;

/**
 * Created by ike on 16-11-10.
 */
public interface IShopRep extends MyRep<Shop,ShopDto>{

    @Cacheable(value = "shopDaoCache")
    Shop findByName(String name);

    @Cacheable(value = "shopDaoCache",key = "#owner.username+#methodName")
    Set<Shop> findByOwner(User owner);

/*    @Caching(evict = {
            @CacheEvict(value="daoCache",key = "#shop.getId()"),
            @CacheEvict(value="daoCache",key = "#shop.getName()")
    })
    @Override
    Shop saveAndFlush(Shop shop);

    @Caching(evict = {
            @CacheEvict(value="daoCache",key="#shop.getName()")
    })
    @Override
    Shop save(Shop shop);*/
}
