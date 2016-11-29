package org.ndshop.shop.service;


import com.dounine.corgi.spring.rpc.Reference;
import com.dounine.corgi.spring.rpc.Service;
import org.apache.commons.lang3.StringUtils;
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
import org.ndshop.user.common.service.IUserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;


/**
 * @Author: [caixianyong]
 * @Date: [2016-11-23 16:51]
 * @Description: [店铺service实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class ShopSerImpl extends ServiceImpl<Shop, ShopDto> implements IShopSer {

    @Autowired
    private IShopRep shopRep;

/*    @Autowired
    private EntityManager em;*/

    @Reference
    private IUserSer userSer;

    @Override
    public Shop findByName(String name) {
        return shopRep.findByName(name);
    }

    @Override
    public Set<Shop> findByUser(User owner) throws SerException {

        String ownerId = owner.getId();
        String username = owner.getUsername();
        User user = null;
        Set<Shop> set = null;

        if (StringUtils.isNotBlank(username)) {
            user = userSer.findByUsername(username);
        } else if (StringUtils.isNotBlank(ownerId)) {
            user = userSer.findById(ownerId);
        } else {
            throw new SerException("缺少user必要参数：username或者id");
        }
        if (user != null) {
            set = shopRep.findByUser(user);
        }

        if (set == null) {
            throw new SerException("参数错误，查找不到对应店铺");
        }
        return set;
    }

    @Override
    @Transactional
    public void addShopByUser(Shop shop, User user) throws SerException {

        //商店数量不超过5个
        /*ShopDto shopDto = new ShopDto();
        if(count(shopDto).get()<5){
            shop.setUser(user);
            shopModifiedAccessTime(shop);
            if(shop.getId()==null){
                save(shop);
            }else{
                update(shop);
            }
        }else{
            throw new SerException("商店数量不超过5个");
        }*/
        ShopDto shopDto = new ShopDto();
        Condition condition = null;
        User findedUser = null;
        if (StringUtils.isNotBlank(user.getUsername())) {
            condition = new Condition("username", DataType.STRING, user.getUsername());
            condition.setRestrict(RestrictionType.LIKE);
            findedUser = userSer.findByUsername(user.getUsername());
        } else if (StringUtils.isNotBlank(user.getId())) {
            condition = new Condition("id", DataType.STRING, user.getId());
            condition.setRestrict(RestrictionType.EQ);
            findedUser = userSer.findById(user.getId());
        } else {
            throw new SerException("缺少user必要参数：username或者id");
        }

        condition.fieldToModels(user.getClass());
        shopDto.getConditions().add(condition);

        Long count = countByCis(shopDto);

        if (findedUser == null) {
            throw new SerException("参数错误，查找不到对应用户");
        } else {
            if (count < 5) {
                shop.setUser(findedUser);
                shopModifiedAccessTime(shop);
                this.save(shop);
            } else {
                throw new SerException("商店数量不超过5个");
            }
        }


    }

    @Override
    @Transactional
    public void shopStatusChange(Shop shop) throws SerException {

        ShopDto shopDto = new ShopDto();
        Condition condition;
        if (StringUtils.isNotBlank(shop.getName())) {
            condition = new Condition("name", DataType.STRING, shop.getName());
        } else if (StringUtils.isNotBlank(shop.getId())) {
            condition = new Condition("id", DataType.STRING, shop.getId());
        } else {
            throw new SerException("缺少shop必要参数，name或者id");
        }

        Shop findedshop = findOne(shopDto);
        if (findedshop == null) {
            throw new SerException("参数错误，找不到此店铺");
        } else {
            //更改店铺状态
            findedshop.setStatus(shop.getStatus() == ShopStatus.OFFLINE ? ShopStatus.ONLINE : ShopStatus.OFFLINE);
            super.update(findedshop);
        }
    }


    //设置最后修改时间
    private void shopModifiedAccessTime(Shop shop) throws SerException {
        shop.setLastModiTime(LocalDateTime.now());
    }

    @Override
    public void update(Shop shop, String oldName) throws SerException {
        shopRep.saveAndFlush(shop);
    }

    @Override
    public Shop save(Shop shop) {
        //不推荐使用，没有确实绑定用户
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
