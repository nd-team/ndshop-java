package org.ndshop.shop.service;


import com.dounine.corgi.spring.rpc.Service;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;

import org.ndshop.shop.dao.IShopRep;
import org.ndshop.shop.dto.ShopDto;
import org.ndshop.shop.entity.Shop;
import org.ndshop.shop.enums.ShopStatus;
import org.ndshop.user.common.dao.IUserRep;
import org.ndshop.user.common.entity.User;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import org.ndshop.shop.entity.*;


/**
 * Created by ike on 16-11-10.
 */
@Service
public class ShopSerImpl extends ServiceImpl<Shop,ShopDto> implements IShopSer {

    @Autowired
    private IShopRep shopRep;

    @Autowired
    private IUserRep userRep;

    @Autowired
    private EntityManager em;


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
    public void addShopByOwnerName(Shop shop, String ownerName) throws SerException {
        User user = userRep.findByUsername(ownerName);
        //商店数量不超过5个
        ShopDto shopDto = new ShopDto();
        if(count(shopDto)<5){
            shop.setOwner(user);
            shopModifiedAccessTime(shop);
            save(shop);
        }else{
            throw new SerException("商店数量不超过5个");
        }
    }

    @Override
    public void shopStatusChange(Shop shop) throws SerException {
        shop.setStatus(shop.getStatus()==ShopStatus.OFFLINE?ShopStatus.ONLINE:ShopStatus.OFFLINE);
        shopModifiedAccessTime(shop);
        update(shop);
    }

    @Override
    public void shopStatusChange(String name) throws SerException {
        Shop shop = shopRep.findByName(name);
        shop.setStatus(shop.getStatus()==ShopStatus.OFFLINE?ShopStatus.ONLINE:ShopStatus.OFFLINE);
        shopModifiedAccessTime(shop);
        update(shop);
    }

    @Override
    public void shopStatusChange(String name, int test) {
        String hql = "update "+Shop.class.getSimpleName()+" shop set shop.status= 1- shop.status,shop.lastModiTime = :datetime  where shop.name = :name";
        Query query = em.createQuery(hql);
        query.setParameter("datetime", LocalDateTime.now());
        query.setParameter("name",name);
        query.executeUpdate();
    }

    //设置最后修改时间
    private void shopModifiedAccessTime(Shop shop) throws SerException {
        shop.setLastModiTime(LocalDateTime.now());
    }

    @Override
    public void update(Shop shop) {
        shopRep.saveAndFlush(shop);
    }

    @Override
    public Shop save(Shop shop){
        return shopRep.save(shop);
    }
}
