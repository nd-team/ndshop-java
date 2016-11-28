package org.ndshop.shop.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.shop.dto.ShopDto;
import org.ndshop.shop.entity.Shop;
import org.ndshop.user.common.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.Optional;
import java.util.Set;

/**
 * @Author: [caixianyong]
 * @Date: [2016-11-23 16:51]
 * @Description: [店铺service接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IShopSer extends IService<Shop, ShopDto> {

    /**
     * 查询对应名称的店铺
     * @param name  店铺名称
     * @return  店铺实体
     */
    /*@Cacheable(value = "shopServiceCache")*/
    default Shop findByName(String name) {
        return null;
    }

    /**
     * 查询用户名下的所有店铺
     * @param owner 用户实体
     * @return  店铺set集合
     */
    /*@Caching(cacheable = {
            @Cacheable(value = "shopServiceCache", key = "#owner.username",condition = "#owner!=null&&#owner.username!=null")
    })*/
    default Set<Shop> findByUser(User owner) throws SerException {
        return null;
    }

    /**
     * 给指定用户添加店铺
     * @param shop  店铺实体
     * @param user  用户实体
     * @throws SerException
     */
    /*@Caching(evict = {
            @CacheEvict(value = "shopServiceCache", key = "#shop"),
            @CacheEvict(value = "shopServiceCache", key = "#user.username",condition = "#user!=null"),
            @CacheEvict(value = "shopServiceCache", key = "#user.id",condition = "#user!=null"),
            @CacheEvict(value = "shopDaoCache", key = "#user")
    })*/
    default void addShopByUser(Shop shop, User user) throws SerException {
        return;
    }

    /**
     * 改变店铺的启用状态
     * @param shop  店铺实体
     * @throws SerException
     */
    default void shopStatusChange(Shop shop) throws SerException {
        return;
    }


    /**
     * 更新店铺
     * @param shop  店铺实体
     * @param oldName   店铺旧名称
     */
    /*@Caching(evict = {
            @CacheEvict(value = "shopServiceCache", key = "#oldName"),
            @CacheEvict(value = "shopServiceCache", key = "#shop.id",condition = "#shop.id!=null&&#shop!=null"),
            @CacheEvict(value = "shopDaoCache", key = "#oldName")
    })*/
    default void update(Shop shop, String oldName) throws SerException {
        return;
    }

    /**
     * 插入新店铺
     * @param shop  店铺实体
     * @return  店铺实体
     */
    /*@Caching(evict = {
            @CacheEvict(value = "shopServiceCache", key = "#shop.name",beforeInvocation = true),
            @CacheEvict(value = "shopDaoCache", key = "#shop.name",beforeInvocation = true)
    })*/
    default Shop save(Shop shop) {
        return null;
    }


    /**
     * test dao cache
     */
    /*void testDao();*/
}
