package org.ndshop.goods.service;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dao.IGoodsCategoryRep;
import org.ndshop.goods.dao.IGoodsRep;
import org.ndshop.goods.dto.GoodsDto;
import org.ndshop.goods.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ike on 16-11-4.
 */
@Service
public class GoodsSerImpl extends ServiceImpl<Goods, GoodsDto> implements IGoodsSer{
    private static Logger logger = Logger.getLogger(GoodsSerImpl.class);

    @Autowired
    private IGoodsRep goodsRep;
    @Autowired
    private IGoodsCategoryRep goodsCategoryRep;
    @Autowired
    private IGoodsBrandSer goodsBrandSer;

    @Cacheable("serviceCache")
    @Override
    public Goods findByGoodName(String goodsName) {
        Goods goods = goodsRep.findByGoodsName( goodsName );
        return goods;
    }

    @Transactional
    @Override
    public void addGoods(Goods goods ,String shopId ,String categoryId ,String goodsBrandId ) throws SerException{
        GoodsDes goodsDes = new GoodsDes();
        goodsDes.setGoods(goods);
        goods.setGoodsDes(goodsDes);

        GoodsInventory goodsInventory = new GoodsInventory();
        goodsInventory.setGoods(goods);
        goods.setGoodsInventory(goodsInventory);

        save(goods);

        GoodsCategory gd = goodsCategoryRep.findById( categoryId );
        goods.setGoodsCategory( gd );
        update( goods );

        if( goodsBrandId != null ){
            GoodsBrand goodsBrand = goodsBrandSer.findById( goodsBrandId );
            goods.setGoodsBrand( goodsBrand );
            update( goods );
        }

        GoodsShops goodsShops = new GoodsShops();
        goodsShops.setGoods(goods);
        Shops shops = new Shops();
        shops.setId( shopId );
        goodsShops.setShops( shops );
        Set<GoodsShops> gs = new HashSet<>();
        gs.add(goodsShops);
        goods.setGoodsShops(gs);


    }

    @Cacheable("serviceCache")
    @Override
    public void findGoodsByFirstCategory(String firstCagetoryName) throws SerException{
        GoodsDto dto = new GoodsDto();

        Condition c = new Condition("name", DataType.STRING , firstCagetoryName);
        c.setRestrict( RestrictionType.EQ );
        c.fieldToModels( GoodsCategory.class );
        dto.getConditions().add( c );

        List<Goods> goods = findByCis( dto );
        logger.info(JSON.toJSONString( goods ));
    }

    @Cacheable("serviceCache")
    @Override
    public void findDese( String goodId ) throws SerException{
        Goods goods = findById( goodId );
        GoodsDes goodsDes = goods.getGoodsDes();
        logger.info(JSON.toJSONString( goodsDes ));
    }

}
