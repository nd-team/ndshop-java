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

    @Cacheable(value = "shopServiceCache", key = "#name")
    default Shop findByName(String name) {
        return null;
    }

    @Cacheable(value = "shopServiceCache", key = "#ownerName+#methodName")
    default Set<Shop> findByOwnerName(String ownerName) {
        return null;
    }

    @Caching(evict = {
            @CacheEvict(value = "shopServiceCache", key = "#ownerName" + "findByOwnerName"),
            @CacheEvict(value = "shopDaoCache", key = "#ownerName" + "findByOwner")
    })
    default void addShopByOwnerName(Shop shop, String ownerName) throws SerException {
        return;
    }

    default void shopStatusChange(Shop shop) throws SerException {
        return;
    }

    default void shopStatusChange(String name) throws SerException {
        return;
    }

    default void shopStatusChange(String name, int test) {
        return;
    }

    @Caching(evict = {
            @CacheEvict(value = "shopServiceCache", key = "#shop.getId()"),
            @CacheEvict(value = "shopServiceCache", key = "#oldName"),
            @CacheEvict(value = "shopDaoCache", key = "#shop.getId()"),
            @CacheEvict(value = "shopDaoCache", key = "#oldName")
    })
    default void updateAndClearCache(Shop shop, String oldName) {
        return;
    }

    @Caching(evict = {
            @CacheEvict(value = "shopServiceCache", key = "#shop.getId()"),
            @CacheEvict(value = "shopServiceCache", key = "#shop.getName()"),
            @CacheEvict(value = "shopDaoCache", key = "#shop.getId()"),
            @CacheEvict(value = "shopDaoCache", key = "#shop.getName()")
    })
    default Shop saveAndClearCache(Shop shop) {
        return null;
    }

    default public Shop testSingle() {
        return null;
    }
}
