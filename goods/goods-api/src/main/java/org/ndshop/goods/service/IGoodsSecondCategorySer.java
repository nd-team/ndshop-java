package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsSecondCategoryDto;
import org.ndshop.goods.entity.GoodsSecondCategory;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品二级分类接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsSecondCategorySer extends IService<GoodsSecondCategory, GoodsSecondCategoryDto> {

    /**
     * 添加商品二级分类
     * @param goodsSecondCategory 商品二级分类
     * @param firstCategoryId 商品一级分类id
     * @throws SerException
     */
    default void addSecondCategory(GoodsSecondCategory goodsSecondCategory, String firstCategoryId) throws SerException {
    }

    ;

    /**
     * 更新二级分类
     * @param goodsSecondCategory 二级分类
     * @throws SerException
     */
    default void updateSecondCategoryName(GoodsSecondCategory goodsSecondCategory) throws SerException {
    }

    ;

    /**
     * 更新二级分类所在一级分类
     * @param secondCategoryId
     * @param firstCategoryId
     * @throws SerException
     */
    default void updateSecondCategoryForeignKey(String secondCategoryId, String firstCategoryId) throws SerException {
    }

    ;

    /**
     * 更新二级分类拼音
     * @param secondCategoryId  二级分类id
     * @param pinyin 二级分类拼音
     * @throws SerException
     */
    default void updateSecondCategoryPinyin(String secondCategoryId, String pinyin) throws SerException {
    }

    ;

    /**
     * 删除二级分类
     * @param secondCategoryId 二级分类id
     * @throws SerException
     */
    default void deleteSecondCategory(String secondCategoryId) throws SerException {
    }

    ;

    /**
     * 根据一级分类查找二级分类
     * @param firstCategoryId 一级分类id
     * @throws SerException
     */
    default void findSecondCategoryByGoodsCategory(String firstCategoryId) throws SerException {
    }

    ;

    /**
     * 根据二级分类名查找二级分类
     * @param secondName 二级分类名
     * @throws SerException
     */
    default void findSecondCategoryByName(String secondName) throws SerException {
    }

    ;

    /**
     * 通过二级分类拼音查找二级分类
     * @param pinyin 二级分类拼音
     * @throws SerException
     */
    default void findSecondCategoryByPinyin(String pinyin) throws SerException {
    }

    ;
}
