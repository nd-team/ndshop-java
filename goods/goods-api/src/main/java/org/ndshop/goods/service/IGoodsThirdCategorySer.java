package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsThirdCategoryDto;
import org.ndshop.goods.entity.GoodsThirdCategory;

/**
 * Created by ike on 16-11-21.
 */
public interface IGoodsThirdCategorySer extends IService<GoodsThirdCategory, GoodsThirdCategoryDto> {

    default void addThirdCategory(GoodsThirdCategory goodsThirdCategory, String secondCategoryId) throws SerException {
    }

    ;

    default void findThirdCategoryBySecondCategory(String secondCategoryId) throws SerException {
    }

    ;

    default void findThirdCategoryByPinyin(String pinyin) throws SerException {
    }

    ;

    default void updateThirdCategory(GoodsThirdCategory goodsThirdCategory) throws SerException {
    }

    ;
}
