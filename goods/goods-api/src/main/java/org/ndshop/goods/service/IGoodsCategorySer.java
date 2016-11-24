package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsCategoryDto;
import org.ndshop.goods.entity.GoodsCategory;

import java.util.List;


/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [一级分类接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsCategorySer extends IService<GoodsCategory, GoodsCategoryDto> {

    /**
     * 添加一级分类
     * @param goodsCategory
     * @throws SerException
     */
    void addCategory ( GoodsCategory goodsCategory) throws SerException;

    /**
     * 更新一级分类
     * @param goodsCategory
     * @throws SerException
     */
    void updateCategory( GoodsCategory goodsCategory ) throws SerException;

    /**
     * 删除一级分类
     * @param goodsCategory
     * @throws SerException
     */
    void deleteCategory( GoodsCategory goodsCategory ) throws SerException;

    /**
     * 根据一级分类查找
     * @param firstCategoryName
     * @throws SerException
     */
    void findCategoryByFirstCategory (String firstCategoryName ) throws  SerException;

    /**
     * 批量添加一级分类
     * @param categoryName
     * @throws SerException
     */
    default void addBatchCategory(List<String> categoryName) throws SerException{};
}
