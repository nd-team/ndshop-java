package org.ndshop.shop.service;


import com.dounine.corgi.spring.rpc.Service;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.shop.dao.IShopRep;
import org.ndshop.shop.dto.ShopDto;
import org.ndshop.shop.entity.Shop;
import org.ndshop.shop.enums.ShopStatus;
import org.ndshop.user.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


/**
 * Created by ike on 16-11-10.
 */
@Service
public class ShopSerImpl extends ServiceImpl<Shop,ShopDto> implements IShopSer {

    @Autowired
    private IShopRep shopRep;

    @Autowired
    private EntityManager em;


    @Override
    public Shop findByName(String shopName) {
        return shopRep.findByName(shopName);
    }

    @Override
    public Set<Shop> findByOwner(User owner) {
        Set<Shop> set = null;
        if(owner!=null){
            set = shopRep.findByOwner(owner);
        }
        return set;
    }

    @Override
    @Transactional
    public void addShopByOwner(Shop shop, User user) throws SerException {
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
    @Transactional
    public void shopStatusChange(Shop shop) throws SerException {
        shop.setStatus(shop.getStatus()==ShopStatus.OFFLINE?ShopStatus.ONLINE:ShopStatus.OFFLINE);
        shopModifiedAccessTime(shop);
        update(shop);
    }

    @Override
    @Transactional
    public void shopStatusChange(String name) throws SerException {
        Shop shop = shopRep.findByName(name);
        shop.setStatus(shop.getStatus()==ShopStatus.OFFLINE?ShopStatus.ONLINE:ShopStatus.OFFLINE);
        shopModifiedAccessTime(shop);
        update(shop);
    }

    @Override
    @Transactional
    public void shopStatusChange(String name, int test) throws SerException {
        /*String hql = "update "+Shop.class.getSimpleName()+" shop set shop.status= 1- shop.status,shop.lastModiTime = :datetime  where shop.name = :name";
        Query query = em.createQuery(hql);
        query.setParameter("datetime", LocalDateTime.now());
        query.setParameter("name",name);
        query.executeUpdate();*/

        ShopDto shopDto = new ShopDto();
        Condition condition = new Condition("name", DataType.STRING, name);
        condition.setRestrict(RestrictionType.EQ);
        shopDto.getConditions().add(condition);
        List<Shop> shopList = findByCis(shopDto);
        if(shopList!=null&&shopList.size()>0){
            for (Shop shop : shopList){
                shop.setStatus(shop.getStatus()==ShopStatus.OFFLINE?ShopStatus.ONLINE:ShopStatus.OFFLINE);
                shop.setLastModiTime(LocalDateTime.now());
                update(shop);
            }
        }
    }

    //设置最后修改时间
    private void shopModifiedAccessTime(Shop shop) throws SerException {
        shop.setLastModiTime(LocalDateTime.now());
    }

    @Override
    public void update(Shop shop, String oldName) {
        shopRep.saveAndFlush(shop);
    }

    @Override
    public Shop save(Shop shop){
        return shopRep.save(shop);
    }


    /***
     * 测试dao缓存,begin
     *
     */
        /*public void testDao(){
            Shop aaa = shopRep.findByName("AAA");
            aaa = shopRep.findByName("AAA");
            aaa = shopRep.findByName("AAA");
            aaa = shopRep.findByName("AAA");
            aaa = shopRep.findByName("AAA");

            //findByName 缓存可用

            aaa.setName("CCC");
            update(aaa,"AAA");
            aaa = shopRep.findByName("AAA");
            System.out.println(aaa.getName());
        }*/
}
