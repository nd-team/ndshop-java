package org.ndshop.goods.service;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsCategoryDto;
import org.ndshop.goods.dto.GoodsThirdCategoryDto;
import org.ndshop.goods.entity.GoodsCategory;
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
    public void addThirdCategory(GoodsThirdCategory goodsThirdCategory , String gcId ) throws SerException {
        goodsThirdCategory.setName( goodsThirdCategory.getName() );

        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setId( gcId );
        goodsThirdCategory.setGoodsCategory( goodsCategory );

        goodsThirdCategory.setCreateTime( LocalDateTime.now() );
        goodsThirdCategory.setModifyTime( LocalDateTime.now() );

        save(  goodsThirdCategory );
        logger.info( goodsThirdCategory);
    }

    @Cacheable("goodsServiceCache")
    @Override
    public void findThirdCategory( String gcId ) throws SerException{
        GoodsThirdCategoryDto dto = new GoodsThirdCategoryDto();
        Condition c = new Condition("id" , DataType.STRING , gcId );
        c.setRestrict(RestrictionType.EQ);
        c.fieldToModels( GoodsCategory.class );
        dto.getConditions().add( c );

        List<GoodsThirdCategory> gs =  findByCis( dto );
        logger.info(JSON.toJSONString( gs ));
    }

    @Transactional
    @Override
    public void updateThirdCategory( GoodsThirdCategory goodsThirdCategory ) throws SerException{
        GoodsThirdCategory gs = findById( goodsThirdCategory.getId() );
        if( gs != null ){
            gs.setName(  goodsThirdCategory.getName() );
            gs.setCreateTime( gs.getCreateTime() );
            gs.setModifyTime( LocalDateTime.now() );

            update( gs );
            logger.info( JSON.toJSONString( gs ));
        }
    }

    @Transactional
    @Override
    public void addBatchThirdCategory(String firstCategoryName , List<String> name ) throws SerException{
        GoodsCategoryDto gcDto = new GoodsCategoryDto();
        Condition c = new Condition("ThirdName",DataType.STRING,firstCategoryName );
        c.setRestrict(RestrictionType.EQ);
        gcDto.getConditions().add( c );
        List<GoodsCategory> goodsCategory = goodsCategorySer.findByCis( gcDto );

        for( String str : name){
            GoodsThirdCategoryDto gscDto = new GoodsThirdCategoryDto();
            Condition c1 = new Condition("name",DataType.STRING , str);
            c1.setRestrict( RestrictionType.EQ );
            gscDto.getConditions().add( c1 );
            List<GoodsThirdCategory> gsc = findByCis( gscDto );

            if( gsc== null || gsc.size()==0 ){
                GoodsThirdCategory gsct = new GoodsThirdCategory();
                gsct.setName( str );
                gsct.setGoodsCategory( goodsCategory.get(0));
                save( gsct );
                logger.info(JSON.toJSONString( gsct ) );
            }
        }

    }


}
