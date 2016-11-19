package org.ndshop.goods.service;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsCategoryDto;
import org.ndshop.goods.entity.GoodsCategory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by ike on 16-11-9.
 */
@Service
public class GoodsCategorySerImpl extends ServiceImpl<GoodsCategory , GoodsCategoryDto> implements IGoodsCategorySer{
    private static Logger logger = Logger.getLogger(GoodsCategorySerImpl.class);

    @Transactional
    @Override
    public void addCategory(GoodsCategory goodsCategory) throws SerException {
        GoodsCategoryDto dto = new GoodsCategoryDto();
        Condition condition1 = new Condition("name", DataType.STRING, goodsCategory.getName());
        Condition condition2 = new Condition("secondName", DataType.STRING, goodsCategory.getSecondName());
        Condition condition3 = new Condition("thirdName", DataType.STRING, goodsCategory.getThirdName());

        dto.getConditions().add(condition2);
        dto.getConditions().add(condition1);
        dto.getConditions().add(condition3);

        GoodsCategory gc = null;
        String nameValue = condition1.getValues()[0];
        String secondNameValue = condition2.getValues()[0];
        String thirdNameValue = condition3.getValues()[0];
        if (!nameValue.equals("") && !secondNameValue.equals("") && !thirdNameValue.equals("")) {
            gc = findOne(dto);
            if (gc == null) {
                save(goodsCategory);
                logger.info(JSON.toJSONString(goodsCategory));
            }
        }


    }

    @Transactional
    @Override
    public void updateCategory( GoodsCategory goodsCategory  ) throws SerException{
        if( goodsCategory != null ){
            GoodsCategory findCategory = findById( goodsCategory.getId() );
            goodsCategory.setModifyTime( LocalDateTime.now() );
            goodsCategory.setCreateTime( findCategory.getCreateTime() );
            goodsCategory.setId( goodsCategory.getId() );
            update( goodsCategory );
            logger.info( JSON.toJSONString( goodsCategory ) );
        }
    }

    @Transactional
    @Override
    public void deleteCategory( GoodsCategory goodsCategory ) throws SerException{
        String cateoryId =goodsCategory.getId();
        goodsCategory = findById( cateoryId );
        if( goodsCategory != null ){
            remove( cateoryId );
        }else{
            logger.info(JSON.toJSONString(goodsCategory));
        }

    }

    @Cacheable("goodsServiceCache")
    @Override
    public void findCategoryByFirstCategory (String firstCategoryName ) throws  SerException{
        Condition condition = new Condition("name",DataType.STRING ,firstCategoryName);
        GoodsCategoryDto dto = new GoodsCategoryDto();
        dto.getConditions().add( condition );
        dto.setLimit(2);
        dto.setPage(1);
        List<GoodsCategory> goodCategory = findByCis( dto,true );
        logger.info(JSON.toJSONString(goodCategory) );
    }
}
