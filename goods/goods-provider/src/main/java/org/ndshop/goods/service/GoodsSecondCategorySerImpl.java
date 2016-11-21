package org.ndshop.goods.service;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsCategoryDto;
import org.ndshop.goods.dto.GoodsSecondCategoryDto;
import org.ndshop.goods.entity.GoodsCategory;
import org.ndshop.goods.entity.GoodsSecondCategory;
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
public class GoodsSecondCategorySerImpl extends ServiceImpl<GoodsSecondCategory,GoodsSecondCategoryDto> implements IGoodsSecondCategorySer {
    private static Logger logger = Logger.getLogger(GoodsSecondCategorySerImpl.class);

    @Autowired
    private IGoodsCategorySer goodsCategorySer;

    @Transactional
    @Override
    public void addSecondCategory( GoodsSecondCategory goodsSecondCategory , String gcId ) throws SerException {
        goodsSecondCategory.setName( goodsSecondCategory.getName() );

        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setId( gcId );
        goodsSecondCategory.setGoodsCategory( goodsCategory );

        goodsSecondCategory.setCreateTime( LocalDateTime.now() );
        goodsSecondCategory.setModifyTime( LocalDateTime.now() );

        save(  goodsSecondCategory );
        logger.info( goodsSecondCategory);
    }

    @Cacheable("goodsServiceCache")
    @Override
    public void findSecondCategory( String gcId ) throws SerException{
        GoodsSecondCategoryDto dto = new GoodsSecondCategoryDto();
        Condition c = new Condition("id" , DataType.STRING , gcId );
        c.setRestrict(RestrictionType.EQ);
        c.fieldToModels( GoodsCategory.class );
        dto.getConditions().add( c );

        List<GoodsSecondCategory> gs =  findByCis( dto );
        logger.info(JSON.toJSONString( gs ));
    }

    @Transactional
    @Override
    public void updateSecondCategory( GoodsSecondCategory goodsSecondCategory ) throws SerException{
        GoodsSecondCategory gs = findById( goodsSecondCategory.getId() );
        if( gs != null ){
            gs.setName(  goodsSecondCategory.getName() );
            gs.setCreateTime( gs.getCreateTime() );
            gs.setModifyTime( LocalDateTime.now() );

            update( gs );
            logger.info( JSON.toJSONString( gs ));
        }
    }

    @Transactional
    @Override
    public void addBatchSecondCategory(String firstCategoryName , List<String> name ) throws SerException{
        GoodsCategoryDto gcDto = new GoodsCategoryDto();
        Condition c = new Condition("secondName",DataType.STRING,firstCategoryName );
        c.setRestrict(RestrictionType.EQ);
        gcDto.getConditions().add( c );
        List<GoodsCategory> goodsCategory = goodsCategorySer.findByCis( gcDto );

        for( String str : name){
            GoodsSecondCategoryDto gscDto = new GoodsSecondCategoryDto();
            Condition c1 = new Condition("name",DataType.STRING , str);
            c1.setRestrict( RestrictionType.EQ );
            gscDto.getConditions().add( c1 );
            List<GoodsSecondCategory> gsc = findByCis( gscDto );

            if( gsc== null || gsc.size()==0 ){
                GoodsSecondCategory gsct = new GoodsSecondCategory();
                gsct.setName( str );
                gsct.setGoodsCategory( goodsCategory.get(0));
                save( gsct );
                logger.info(JSON.toJSONString( gsct ) );
            }
        }

    }


}
