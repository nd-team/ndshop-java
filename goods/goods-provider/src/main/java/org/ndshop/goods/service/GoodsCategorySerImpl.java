package org.ndshop.goods.service;

import com.dounine.corgi.spring.rpc.Reference;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsCategoryDto;
import org.ndshop.goods.entity.GoodsCategory;
import org.ndshop.user.common.service.IUserSer;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品一级分类业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class GoodsCategorySerImpl extends ServiceImpl<GoodsCategory, GoodsCategoryDto> implements IGoodsCategorySer {
    private static Logger logger = Logger.getLogger(GoodsCategorySerImpl.class);

    @Reference
    IUserSer userSer;

    @Transactional
    @Override
    public void addParentCategory(GoodsCategory goodsCategory) throws SerException {
        if (StringUtils.isNotBlank(goodsCategory.getName()) ){
            goodsCategory.setParentNodeNum(0L);
            goodsCategory.setCreateTime(LocalDateTime.now());
            goodsCategory.setModifyTime(LocalDateTime.now());
            save(goodsCategory);
        } else {
            throw new SerException("分类数据为空");
        }
    }

    @Transactional
    @Override
    public void addChildCategory(GoodsCategory goodsCategory, String parentId) throws SerException {
        GoodsCategory gc = findById(parentId);

        if ( gc != null ) {
            goodsCategory.setGoodsCategory(gc);
            goodsCategory.setCreateTime(LocalDateTime.now());
            goodsCategory.setModifyTime(LocalDateTime.now());
            goodsCategory.setParentNodeNum(gc.getParentNodeNum() + 1);
            save(goodsCategory);
        }else {
            throw new SerException("父分类不存在");
        }
    }

    @Cacheable("goodsServiceCache")
    @Override
    public List<GoodsCategory> findChildCategoryByParent(String paretCategoryId ) throws SerException{
        GoodsCategoryDto dto = new GoodsCategoryDto();
        Condition c = new Condition("id", DataType.STRING,paretCategoryId);
        c.fieldToModels( GoodsCategory.class );
        dto.getConditions().add( c );
        List<GoodsCategory> list = findByCis(dto);
        return list;
    }

    @Cacheable("goodsServiceCache")
    @Override
    public List<GoodsCategory> findCategoryByName( String categoryName ) throws SerException{
        GoodsCategoryDto dto = new GoodsCategoryDto();
        Condition c = new Condition("name", DataType.STRING, categoryName);
        c.setRestrict(RestrictionType.LIKE);
        dto.getConditions().add( c );
        List<GoodsCategory> list = findByCis( dto );

        return list ;
    }


    @Cacheable("goodsServiceCache")
    @Override
    public List<GoodsCategory> findCategoryByNodeNum(Long parentNodeNum ) throws SerException{
        Condition c = new Condition("parentNodeNum", DataType.LONG, parentNodeNum);
        GoodsCategoryDto dto = new GoodsCategoryDto();
        dto.getConditions().add( c );
        List<GoodsCategory> list = findByCis( dto );

//        userSer.findAll();

        return list;
    }
}
