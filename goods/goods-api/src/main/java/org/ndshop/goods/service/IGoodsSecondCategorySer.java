package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsSecondCategoryDto;
import org.ndshop.goods.entity.GoodsSecondCategory;

import java.util.List;

/**
 * Created by ike on 16-11-21.
 */
public interface IGoodsSecondCategorySer extends IService<GoodsSecondCategory, GoodsSecondCategoryDto> {

    default void addSecondCategory(GoodsSecondCategory goodsSecondCategory, String gcId) throws SerException {
    }

    ;

    default void findSecondCategory(String gcId) throws SerException {
    }

    ;

    default void updateSecondCategory(GoodsSecondCategory goodsSecondCategory) throws SerException {
    }

    ;
    default void addBatchSecondCategory(String firstCategoryName, List<String> name) throws SerException{};
}
