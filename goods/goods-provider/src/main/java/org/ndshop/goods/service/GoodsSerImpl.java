package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dao.IGoodsRep;
import org.ndshop.goods.dto.GoodsDto;
import org.ndshop.goods.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ike on 16-11-4.
 */
@Service
public class GoodsSerImpl extends ServiceImpl<Goods, GoodsDto> implements IGoodsSer{
   @Autowired
   private IGoodsRep goodsRep;

    @Override
    public Goods findByGoodName(String goodNames) {
        Goods goods = goodsRep.findByGoodsName( goodNames );
        return goods;
    }

    @Override
    public void addGoods(Goods goods ,String shopId) throws SerException{
        GoodsDes goodsDes = new GoodsDes();
        goodsDes.setGoods(goods);
        goods.setGoodsDes(goodsDes);

        GoodsInventory goodsInventory = new GoodsInventory();
        goodsInventory.setGoods(goods);
        goods.setGoodsInventory(goodsInventory);

        GoodsShops goodsShops = new GoodsShops();
        goodsShops.setGoods(goods);
        Shops shops = new Shops();
        shops.setId( shopId );
        goodsShops.setShops( shops );
        Set<GoodsShops> gs = new HashSet<>();
        gs.add(goodsShops);
        goods.setGoodsShops(gs);

        save(goods);
    }

    @Override
    public List<Goods> findByCategory(String firstCategory, String secondCategory) throws SerException{
        GoodsDto dto = new GoodsDto();
        List<Condition> conditions = dto.getConditions();
        Condition condition = new Condition("goods.firstCategory", DataType.STRING,firstCategory);
        condition.setRestrict(RestrictionType.EQ);
        conditions.add(condition);

        Condition condition1 = new Condition("goods.secondCategory", DataType.STRING,secondCategory);
        condition1.setRestrict(RestrictionType.EQ);
        conditions.add(condition1);

        List<Goods> list = findByCis(dto , true);

        return list;
    }
}
