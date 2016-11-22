package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsSecondCategoryDto;
import org.ndshop.goods.entity.GoodsSecondCategory;

/**
 * Created by ike on 16-11-21.
 */
public interface IGoodsSecondCategorySer extends IService<GoodsSecondCategory, GoodsSecondCategoryDto> {

    default void addSecondCategory(GoodsSecondCategory goodsSecondCategory, String firstCategoryId) throws SerException {
    }

    ;

    default void updateSecondCategoryName(GoodsSecondCategory goodsSecondCategory) throws SerException {
    }

    ;

    default void updateSecondCategoryForeignKey(String secondCategoryId, String firstCategoryId) throws SerException {
    }

    ;

    default void updateSecondCategoryPinyin(String secondCategoryId, String pinyin) throws SerException {
    }

    ;

    default void deleteSecondCategory(String secondCategoryId) throws SerException {
    }

    ;

    default void findSecondCategoryByGoodsCategory(String firstCategoryId) throws SerException {
    }

    ;

    default void findSecondCategoryByName(String secondName) throws SerException {
    }

    ;

    default void findSecondCategoryByPinyin(String pinyin) throws SerException {
    }

    ;
}
