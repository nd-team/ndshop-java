package org.ndshop.goods.service;

import com.dounine.corgi.spring.rpc.Reference;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsCategoryDto;
import org.ndshop.goods.entity.GoodsCategory;
import org.ndshop.user.common.entity.User;
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

    @Override
    public void addTest() throws SerException {
        User user1 = new User();
        user1.setEmail("xianml@123.com");
        user1.setUsername("liguiqin11");
        user1.setPassword("123456");
        user1.setNickname("liguiqin11");
        user1.setAge(11);
        userSer.save(user1);
        String categoryName = "电器";
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setName(  categoryName );
        goodsCategory.setParentNodeNum(0L);
        goodsCategory.setCreateTime( LocalDateTime.now() );
        goodsCategory.setModifyTime(LocalDateTime.now() );
        save(goodsCategory);
    }

    @Transactional
    @Override
    public void addParentCategory(GoodsCategory goodsCategory) throws SerException {
        if (StringUtils.isBlank(goodsCategory.getName()) || StringUtils.isEmpty(goodsCategory.getName())) {
            logger.error("category name is '' or null ,you cat not insert ");
        } else {
            goodsCategory.setParentNodeNum(0L);
            goodsCategory.setCreateTime(LocalDateTime.now());
            goodsCategory.setModifyTime(LocalDateTime.now());
            save(goodsCategory);
        }
    }

    @Transactional
    @Override
    public void addChildCategory(GoodsCategory goodsCategory, String parentId) throws SerException {
        GoodsCategory gc = findById(parentId);

        if ( gc != null ) {
            goodsCategory.setParent(gc);
            goodsCategory.setCreateTime(LocalDateTime.now());
            goodsCategory.setModifyTime(LocalDateTime.now());
            goodsCategory.setParentNodeNum(gc.getParentNodeNum() + 1);
            save(goodsCategory);
        }
    }

    @Cacheable("goodsServiceCache")
    @Override
    public List<GoodsCategory> findCategoryByNodeNum(Long parentNodeNum ) throws SerException{
        Condition c = new Condition("parentNodeNum", DataType.LONG, parentNodeNum);
        GoodsCategoryDto dto = new GoodsCategoryDto();
        dto.getConditions().add( c );
        List<GoodsCategory> opGoodsCategory = findByCis( dto );

//        userSer.findAll();

        return opGoodsCategory;
    }
}
