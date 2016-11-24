package org.ndshop.goods.service;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsThirdCategoryDto;
import org.ndshop.goods.entity.GoodsSecondCategory;
import org.ndshop.goods.entity.GoodsThirdCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品三级分类业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class GoodsThirdCategorySerImpl extends ServiceImpl<GoodsThirdCategory,GoodsThirdCategoryDto> implements IGoodsThirdCategorySer {
    private static Logger logger = Logger.getLogger(GoodsThirdCategorySerImpl.class);

    @Autowired
    private IGoodsCategorySer goodsCategorySer;

    @Transactional
    @Override
    public void addThirdCategory( GoodsThirdCategory goodsThirdCategory , String secondCategoryId ) throws SerException{
        goodsThirdCategory.setThirdName( goodsThirdCategory.getThirdName());
        goodsThirdCategory.setPinyin( goodsThirdCategory.getPinyin() );

        GoodsSecondCategory goodsSecondCategory = new GoodsSecondCategory();
        goodsSecondCategory.setId( secondCategoryId );
        goodsThirdCategory.setGoodsSecondCategory( goodsSecondCategory );

        goodsThirdCategory.setCreateTime( LocalDateTime.now() );
        goodsThirdCategory.setModifyTime( LocalDateTime.now() );

        save(  goodsThirdCategory );
        logger.info( goodsThirdCategory);
    }

    @Cacheable("goodsServiceCache")
    @Override
    public void findThirdCategoryBySecondCategory( String secondCategoryId ) throws SerException{
        GoodsThirdCategoryDto dto = new GoodsThirdCategoryDto();
        Condition c = new Condition("id" , DataType.STRING , secondCategoryId );
        c.setRestrict(RestrictionType.EQ);
        c.fieldToModels( GoodsSecondCategory.class );
        dto.getConditions().add( c );

        Optional<List<GoodsThirdCategory>> gs =  findByCis( dto );
        logger.info(JSON.toJSONString( gs.get() ));
    }

    @Cacheable("goodsServiceCache")
    @Override
    public void findThirdCategoryByPinyin( String pinyin ) throws SerException{
        GoodsThirdCategoryDto dto = new GoodsThirdCategoryDto();
        Condition c = new Condition("pinyin" , DataType.STRING , pinyin );
        c.setRestrict(RestrictionType.LIKE);
        dto.getConditions().add( c );

        Optional<List<GoodsThirdCategory>> gs =  findByCis( dto );
        logger.info(JSON.toJSONString( gs.get() ));
    }


    @Transactional
    @Override
    public void updateThirdCategory( GoodsThirdCategory goodsThirdCategory ) throws SerException{
        Optional<GoodsThirdCategory> opGoodsThirdCategory = findById( goodsThirdCategory.getId() );

        if( opGoodsThirdCategory.isPresent() ){
            GoodsThirdCategory gtc = opGoodsThirdCategory.get();
            gtc.setThirdName(  goodsThirdCategory.getThirdName() );
            gtc.setCreateTime( gtc.getCreateTime() );
            gtc.setModifyTime( LocalDateTime.now() );

            update( gtc );
            logger.info( JSON.toJSONString( gtc ));
        }
    }




}
