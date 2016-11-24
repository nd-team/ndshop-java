package org.ndshop.goods.service;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsSecondCategoryDto;
import org.ndshop.goods.entity.GoodsCategory;
import org.ndshop.goods.entity.GoodsSecondCategory;
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
 * @Description: [商品二级分类业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class GoodsSecondCategorySerImpl extends ServiceImpl<GoodsSecondCategory,GoodsSecondCategoryDto> implements IGoodsSecondCategorySer {
    private static Logger logger = Logger.getLogger(GoodsSecondCategorySerImpl.class);

    @Autowired
    private IGoodsCategorySer goodsCategorySer;

    @Transactional
    @Override
    public void addSecondCategory(GoodsSecondCategory goodsSecondCategory , String firstCategoryId ) throws SerException {
        goodsSecondCategory.setSecondName( goodsSecondCategory.getSecondName() );
        goodsSecondCategory.setCreateTime(LocalDateTime.now());
        goodsSecondCategory.setModifyTime(LocalDateTime.now());

        GoodsCategory gc = new GoodsCategory();
        gc.setId(firstCategoryId);
        goodsSecondCategory.setGoodsCategory(gc);

        if( goodsSecondCategory.getSecondName() !=null && goodsSecondCategory.getSecondName() !="" ){
            save(goodsSecondCategory);
        }

        logger.info(JSON.toJSONString(goodsSecondCategory));
    }

    @Transactional
    @Override
    public void updateSecondCategoryName( GoodsSecondCategory goodsSecondCategory ) throws SerException {
        Optional<GoodsSecondCategory> opGoodsSecondCategory = findById( goodsSecondCategory.getId() );
        if ( opGoodsSecondCategory.isPresent() ) {
            GoodsSecondCategory gsc = opGoodsSecondCategory.get();
            gsc.setSecondName( goodsSecondCategory.getSecondName() );
            gsc.setModifyTime(LocalDateTime.now());
            update(gsc);
        }

    }

    @Transactional
    @Override
    public void updateSecondCategoryForeignKey(String secondCategoryId, String firstCategoryId  ) throws SerException {
        Optional<GoodsSecondCategory> opGoodsSecondCategory = findById(secondCategoryId);

        if ( opGoodsSecondCategory.isPresent() ) {
            GoodsSecondCategory gsc = opGoodsSecondCategory.get();
            GoodsCategory gc = new GoodsCategory();
            gc.setId(firstCategoryId);

            gsc.setGoodsCategory(gc);
            gsc.setModifyTime(LocalDateTime.now());
            update(gsc);
        }


    }

    @Transactional
    @Override
    public void updateSecondCategoryPinyin( String secondCategoryId , String pinyin  ) throws SerException {
        Optional<GoodsSecondCategory> opGoodsSecondCategory = findById(secondCategoryId);

        if ( opGoodsSecondCategory.isPresent() ) {
            GoodsSecondCategory gsc = opGoodsSecondCategory.get();
            gsc.setPinyin(pinyin);
            gsc.setModifyTime(LocalDateTime.now());
            update(gsc);
        }
    }

    @Transactional
    @Override
    public void deleteSecondCategory( String secondCategoryId ) throws SerException {
        Optional<GoodsSecondCategory> opGoodsSecondCategory = findById(secondCategoryId);
        if ( opGoodsSecondCategory.isPresent() ) {
            remove(secondCategoryId);
        }
    }

    @Cacheable("goodsServiceCache")
    @Override
    public void findSecondCategoryByGoodsCategory( String firstCategoryId ) throws SerException {
        GoodsSecondCategoryDto dto = new GoodsSecondCategoryDto();
        Condition c = new Condition("id", DataType.STRING, firstCategoryId);
        c.setRestrict(RestrictionType.EQ);
        c.fieldToModels(GoodsCategory.class);

        dto.getConditions().add(c);
        Optional<List<GoodsSecondCategory>> opList = findByCis( dto );
        if ( opList.isPresent() ) {
            logger.info(JSON.toJSONString( opList.get() ));
        }
    }

    @Cacheable("goodsServiceCache")
    @Override
    public void findSecondCategoryByName( String secondName ) throws SerException {
        GoodsSecondCategoryDto dto = new GoodsSecondCategoryDto();
        Condition c = new Condition("secondName", DataType.STRING, secondName);
        c.setRestrict(RestrictionType.LIKE);

        dto.getConditions().add(c);
        Optional<List<GoodsSecondCategory>> opList = findByCis( dto );
        if ( opList.isPresent() ) {
            logger.info(JSON.toJSONString(  opList.get() ));
        }
    }

    @Cacheable("goodsServiceCache")
    @Override
    public void findSecondCategoryByPinyin(String pinyin) throws SerException {
        GoodsSecondCategoryDto dto = new GoodsSecondCategoryDto();
        Condition c = new Condition("pinyin", DataType.STRING, pinyin);
        c.setRestrict(RestrictionType.LIKE);

        dto.getConditions().add(c);
        Optional<List<GoodsSecondCategory>> opList = findByCis( dto );
        if ( opList.isPresent() ) {
            logger.info(JSON.toJSONString(  opList.get() ));
        }
    }
}
