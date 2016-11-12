package org.ndshop.shop.dao;

import org.ndshop.dbs.jpa.dao.MyRep;
import org.ndshop.shop.dto.ShopDto;
import org.ndshop.shop.entity.Shop;

/**
 * Created by ike on 16-11-10.
 */
public interface IShopRep extends MyRep<Shop,ShopDto>{

    Shop findByName(String name);
}
