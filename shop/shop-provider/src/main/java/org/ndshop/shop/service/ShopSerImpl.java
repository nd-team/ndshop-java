package org.ndshop.shop.service;



import com.dounine.corgi.spring.rpc.Reference;
import org.ndshop.dbs.jpa.service.ServiceImpl;

import org.ndshop.shop.dao.IShopRep;
import org.ndshop.shop.dto.ShopDto;
import org.ndshop.shop.entity.Shop;
import org.ndshop.user.common.dao.IUserRep;
import org.ndshop.user.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by ike on 16-11-10.
 */
@Service
public class ShopSerImpl extends ServiceImpl<Shop,ShopDto> implements IShopSer {

    @Autowired
    private IShopRep shopRep;

    @Reference
    private IUserRep userRep;

    @Override
    public Shop findByName(String shopName) {
        return shopRep.findByName(shopName);
    }

    @Override
    public Set<Shop> findByOwnerName(String ownerName) {
        Set<Shop> set = null;
        User owner = userRep.findByUsername(ownerName);
        if(owner!=null){
            set = shopRep.findByOwner(owner);
        }
        return set;
    }

    @Override
    public void addShopByOwnerName(Shop shop, String ownerName) {
        User user = userRep.findByUsername(ownerName);

        //商店数量不超过5个

    }
}
