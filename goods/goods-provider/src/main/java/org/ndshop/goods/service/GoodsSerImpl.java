package org.ndshop.goods.service;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.dto.Restrict;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dao.IGoodsRep;
import org.ndshop.goods.dto.GoodsDto;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsBrands;
import org.ndshop.goods.entity.GoodsCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-26 17:25]
 * @Description: [商品业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class GoodsSerImpl extends ServiceImpl<Goods, GoodsDto> implements IGoodsSer {
    private static Logger logger = Logger.getLogger(GoodsSerImpl.class);

    @Autowired
    private IGoodsRep goodsRep;

    @Transactional
    @Override
    public void addGoods(Goods goods , String brandId ,String categoryId ) throws SerException{
        Goods gs = new Goods();
        gs.setGoodsNum( goods.getGoodsNum() );
        gs.setName(  goods.getName() );
        gs.setGoodsDescription( goods.getGoodsDescription() );
        gs.setCreateTime( LocalDateTime.now() );
        gs.setModifyTime( LocalDateTime.now() );

        GoodsBrands goodsBrands = new GoodsBrands();
        goodsBrands.setId( brandId );
        gs.setGoodsBrands( goodsBrands );

        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setId( categoryId );
        gs.setGoodsCategory(  goodsCategory );

        save( goods );
    }

    @Cacheable("goodsServiceCache")
    @Override
    public Goods findGoodsById() throws SerException {
        String gid = "2a27c9d2-5536-478d-982e-0645731e4679";
        Goods goods = goodsRep.findById( gid );
        logger.info(JSON.toJSONString( goods ));
        return goods;
    }

    @Cacheable("goodsServiceCache")
    @Override
    public List<Goods> findByBrandName(String brandName )throws SerException{
        GoodsDto dto = new GoodsDto();
        dto.getConditions().add(Restrict.like("name",brandName));
        List<Goods> goods = findByCis( dto );
        logger.info(JSON.toJSONString( goods ) );

        return goods;
    }

}
