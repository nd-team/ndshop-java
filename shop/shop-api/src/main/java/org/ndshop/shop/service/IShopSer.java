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
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

/**
 * Created by ike on 16-11-10.
 */
public interface IShopSer extends IService<Shop, ShopDto> {

    @Cacheable("serviceCache")
    default Shop findByName(String name) {
        return null;
    }

    @Cacheable("serviceCache")
    default Set<Shop> findByOwnerName(String ownerName) {
        return null;
    }

    @CacheEvict("serviceCache")
    @Transactional
    default void addShopByOwnerName(Shop shop, String ownerName) throws SerException {
        return;
    }

    @CacheEvict("serviceCache")
    @Transactional
    default void shopStatusChange(Shop shop) throws SerException {
        return;
    }

    @CacheEvict("serviceCache")
    @Transactional
    default void shopStatusChange(String name) throws SerException {
        return;
    }

    @CacheEvict("serviceCache")
    @Transactional
    default void shopStatusChange(String name,int test){
        return;
    }

    @CacheEvict("serviceCache")
    default void update(Shop shop){
        return;
    }

    @CacheEvict("serviceCache")
    default Shop save(Shop shop){
        return null;
    }
}
