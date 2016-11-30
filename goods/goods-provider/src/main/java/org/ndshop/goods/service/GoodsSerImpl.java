package org.ndshop.goods.service;

import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dao.IGoodsRep;
import org.ndshop.goods.dto.GoodsDto;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsBrands;
import org.ndshop.goods.entity.GoodsCategory;
import org.ndshop.goods.enums.*;
import org.ndshop.user.common.entity.User;
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
    public Goods addGoods(Goods goods , GoodsDto goodsDto ) throws SerException{
        Goods gs = new Goods();
        gs.setGoodsNum( goods.getGoodsNum() );
        gs.setName(  goods.getName() );
        gs.setGoodsDescription( goods.getGoodsDescription() );
        goods.setGoodsCode(goods.getGoodsCode());
        goods.setPrice( goods.getPrice() );
        goods.setDiscountPrice( goods.getDiscountPrice() );
        goods.setQuantity( goods.getQuantity() );
        goods.setGoodsOnSaleStatus(GoodsOnSaleStatus.ONSALE );
        goods.setGoodsSpecialSaleStatus(GoodsSpecialSaleStatus.NORMALSALE );
        goods.setGoodsNewFlagStatus(GoodsNewFlagStatus.isOld );
        goods.setGoodsHotSaleStatus(GoodsHotSaleStatus.NORMALSALE );
        goods.setGoodsRecommendStatus(GoodsRecommendStatus.isnormal );
        goods.setCreateTime( LocalDateTime.now() );
        gs.setModifyTime( LocalDateTime.now() );

        GoodsBrands goodsBrands = new GoodsBrands();
        goodsBrands.setId( goodsDto.getBrandId() );
        gs.setGoodsBrands( goodsBrands );

        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setId( goodsDto.getCategoryId() );
        gs.setGoodsCategory(  goodsCategory );

        User user = new User();
        user.setId( goodsDto.getUserId() );
        goods.setUser( user );

        return this.save( goods );
    }

    @Cacheable("goodsServiceCache")
    @Override
    public Goods findGoodsById(String goodsId) throws SerException {
        Goods goods = goodsRep.findById( goodsId );
        return goods;
    }

    @Cacheable("goodsServiceCache")
    @Override
    public List<Goods> findByBrandName(String brandName )throws SerException{
        Condition c = new Condition("name", DataType.STRING , brandName );
        c.fieldToModels( GoodsBrands.class);
        c.setRestrict(RestrictionType.LIKE);

        GoodsDto dto = new GoodsDto();
        dto.getConditions().add( c );
        List<Goods> goods = findByCis( dto );

        return goods;
    }

}
