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

/**
 * Created by ike on 16-11-21.
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

        List<GoodsThirdCategory> gs =  findByCis( dto );
        logger.info(JSON.toJSONString( gs ));
    }

    @Cacheable("goodsServiceCache")
    @Override
    public void findThirdCategoryByPinyin( String pinyin ) throws SerException{
        GoodsThirdCategoryDto dto = new GoodsThirdCategoryDto();
        Condition c = new Condition("pinyin" , DataType.STRING , pinyin );
        c.setRestrict(RestrictionType.LIKE);
        dto.getConditions().add( c );

        List<GoodsThirdCategory> gs =  findByCis( dto );
        logger.info(JSON.toJSONString( gs ));
    }


    @Transactional
    @Override
    public void updateThirdCategory( GoodsThirdCategory goodsThirdCategory ) throws SerException{
        GoodsThirdCategory gs = findById( goodsThirdCategory.getId() );

        if( gs != null ){
            gs.setThirdName(  goodsThirdCategory.getThirdName() );
            gs.setCreateTime( gs.getCreateTime() );
            gs.setModifyTime( LocalDateTime.now() );

            update( gs );
            logger.info( JSON.toJSONString( gs ));
        }
    }




}
