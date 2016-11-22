package org.ndshop.shop.service;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.shop.dto.ShopDto;
import org.ndshop.shop.entity.Shop;
import org.ndshop.user.common.dto.UserDto;
import org.ndshop.user.common.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

/**
 * Created by ike on 16-11-10.
 */
public interface IShopSer extends IService<Shop, ShopDto> {

    @Cacheable(value = "shopServiceCache")
    default Shop findByName(String name) {
        return null;
    }

    @Caching(cacheable = {
            @Cacheable(value = "shopServiceCache", key = "#owner.username"),
            @Cacheable(value = "shopServiceCache", key = "#owner.id")
    })
    default Set<Shop> findByOwner(User owner) {
        return null;
    }

    @Caching(evict = {
            @CacheEvict(value = "shopServiceCache", key = "#user.username"),
            @CacheEvict(value = "shopServiceCache", key = "#user.id"),
            @CacheEvict(value = "shopDaoCache", key = "#user")
    })
    default void addShopByOwner(Shop shop, User user) throws SerException {
        return;
    }

    default void shopStatusChange(Shop shop) throws SerException {
        return;
    }

    default void shopStatusChange(String name) throws SerException {
        return;
    }

    default void shopStatusChange(String name, int test) throws SerException {
        return;
    }

    @Caching(evict = {
            @CacheEvict(value = "shopServiceCache", key = "#oldName"),
            @CacheEvict(value = "shopServiceCache", key = "#shop.id",condition = "#shop.id!=null&&#shop!=null"),
            @CacheEvict(value = "shopDaoCache", key = "#oldName")
    })
    default void update(Shop shop, String oldName) {
        return;
    }

    @Caching(evict = {
            @CacheEvict(value = "shopServiceCache", key = "#shop.name"),
            @CacheEvict(value = "shopDaoCache", key = "#shop.name")
    })
    default Shop save(Shop shop) {
        return null;
    }


    /**
     * test dao cache
     */
    /*void testDao();*/
}
