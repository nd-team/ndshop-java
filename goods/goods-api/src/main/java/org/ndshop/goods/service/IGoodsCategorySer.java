package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsCategoryDto;
import org.ndshop.goods.entity.GoodsCategory;

import java.util.List;
import java.util.Optional;


/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [一级分类接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsCategorySer extends IService<GoodsCategory, GoodsCategoryDto> {

    /**
     * 添加父级分类
     * @param goodsCategory
     * @throws SerException
     */
    default void addParentCategory ( GoodsCategory goodsCategory) throws SerException{};

    /**
     * 添加子分类
     * @param goodsCategory 分类
     * @param parentId 父分类id
     * @throws SerException
     */
    default void addChildCategory(GoodsCategory goodsCategory ,String parentId ) throws SerException{};

    /**
     * 根据父节点的个数查找所有分类
     * @param parentNodeNum 父节点数
     * @return
     * @throws SerException
     */
    default Optional<List<GoodsCategory>> findCategoryByNodeNum(Long parentNodeNum ) throws SerException{return null;};
}
