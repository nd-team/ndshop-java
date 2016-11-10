package org.ndshop.shop.service;


import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.shop.dto.ShopDto;
import org.ndshop.shop.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ike on 16-11-10.
 */
@Service
public class ShopSerImpl extends ServiceImpl<Shop,ShopDto> implements IShopSer {

    @Autowired
    private IShopSer shopSer;
    @Override
    public Shop findByName(String shopName) {
        return shopSer.findByName(shopName);
    }
}
