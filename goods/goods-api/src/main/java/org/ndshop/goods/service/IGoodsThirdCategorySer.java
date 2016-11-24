package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsThirdCategoryDto;
import org.ndshop.goods.entity.GoodsThirdCategory;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [三级分类接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsThirdCategorySer extends IService<GoodsThirdCategory, GoodsThirdCategoryDto> {

    /**
     * 添加三级分类
     * @param goodsThirdCategory 三级分类
     * @param secondCategoryId 二级分类id
     * @throws SerException
     */
    default void addThirdCategory(GoodsThirdCategory goodsThirdCategory, String secondCategoryId) throws SerException {
    }

    ;

    /**
     * 根据二级分类查找三级分类
     * @param secondCategoryId 二级分类id
     * @throws SerException
     */
    default void findThirdCategoryBySecondCategory(String secondCategoryId) throws SerException {
    }

    ;

    /**
     * 根据拼音查找三级分类
     * @param pinyin 三级分类拼音
     * @throws SerException
     */
    default void findThirdCategoryByPinyin(String pinyin) throws SerException {
    }

    ;

    /**
     * 更新三级分类
     * @param goodsThirdCategory 三级分类
     * @throws SerException
     */
    default void updateThirdCategory(GoodsThirdCategory goodsThirdCategory) throws SerException {
    }

    ;
}
