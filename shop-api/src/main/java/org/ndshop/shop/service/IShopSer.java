package org.ndshop.shop.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.shop.dto.ShopDto;
import org.ndshop.shop.entity.Shop;

import java.util.List;

/**
 * Created by ike on 16-11-9.
 */
public interface IShopSer extends IService{

    //查询用户所有店铺
    List<Shop> findAllShopByUser() throws SerException;

    //新增店铺
    Shop addShop() throws SerException;

    //删除选中店铺
    void removeSelected(List<ShopDto> list) throws SerException;



}
