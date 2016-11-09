package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dao.IGoodsRep;
import org.ndshop.goods.dto.GoodsDto;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsDes;
import org.ndshop.goods.entity.GoodsInventory;
import org.ndshop.goods.entity.GoodsShops;
import org.ndshop.goods.enums.GoodsCategory;
import org.ndshop.goods.enums.GoodsElectricType;
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
    public void addGoods(Goods goods) throws SerException{
        goods.setGoodsName( goods.getGoodsName() );
        goods.setPrice(2000.0);
        goods.setGoodsLength(3.4);
        goods.setGoodsWidth(1.5);
        goods.setGoodsHeight(5.6);
        goods.setGoodsWeight(0.8);
        goods.setFirstCategory(GoodsCategory.ELECTRC.toString() );
        goods.setSecondCategory(GoodsElectricType.BIGELECTRIC.toString());

        GoodsDes goodsDes = new GoodsDes();
        goodsDes.setGoods(goods);
        goods.setGoodsDes(goodsDes);

        GoodsInventory goodsInventory = new GoodsInventory();
        goodsInventory.setGoods(goods);
        goods.setGoodsInventory(goodsInventory);

        GoodsShops goodsShops = new GoodsShops();
        goodsShops.setGoods(goods);
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
