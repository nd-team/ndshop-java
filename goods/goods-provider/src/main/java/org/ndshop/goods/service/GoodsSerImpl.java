package org.ndshop.goods.service;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dao.IGoodsRep;
import org.ndshop.goods.dao.IGoodsThirdCategoryRep;
import org.ndshop.goods.dto.GoodsDto;
import org.ndshop.goods.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class GoodsSerImpl extends ServiceImpl<Goods, GoodsDto> implements IGoodsSer {
    private static Logger logger = Logger.getLogger(GoodsSerImpl.class);

    @Autowired
    private IGoodsRep goodsRep;
    @Autowired
    private IGoodsThirdCategoryRep goodsThirdCategoryRep;
    @Autowired
    protected IGoodsBrandSer goodsBrandSer;

    @Cacheable("goodsServiceCache")
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

//        GoodsThirdCategory gd = goodsThirdCategoryRep.findById( categoryId );
//        goods.setGoodsThirdCategory( gd );
//        update( goods );

        if( goodsBrandId != null ){
            Optional<GoodsBrand> opGoodsBrand = goodsBrandSer.findById( goodsBrandId );
            goods.setGoodsBrand( opGoodsBrand.get() );
            update( goods );
        }

//        GoodsShops goodsShops = new GoodsShops();
//        goodsShops.setGoods(goods);
//        Shops shops = new Shops();
//        shops.setId( shopId );
//        goodsShops.setShops( shops );
//        Set<GoodsShops> gs = new HashSet<>();
//        gs.add(goodsShops);
//        goods.setGoodsShops(gs);


    }

    @Cacheable("goodsServiceCache")
    @Override
    public void findGoodsByThirdCategory(String thirdCagetoryName) throws SerException{
        GoodsDto dto = new GoodsDto();

        Condition c = new Condition("thirdName", DataType.STRING , thirdCagetoryName);
        c.setRestrict( RestrictionType.EQ );
        c.fieldToModels( GoodsThirdCategory.class );
        dto.getConditions().add( c );

        Optional<List<Goods>> opGoods = findByCis( dto );
        if (  opGoods.isPresent() ) {
            logger.info(JSON.toJSONString( opGoods.get() ));
        }
    }

    @Cacheable("goodsServiceCache")
    @Override
    public void findDese( String goodId ) throws SerException{
        Optional<Goods> opGoods = findById( goodId );
        GoodsDes goodsDes = opGoods.get().getGoodsDes();
        logger.info(JSON.toJSONString( goodsDes ));
    }

}
