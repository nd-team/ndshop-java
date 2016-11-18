package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsCategoryDto;
import org.ndshop.goods.entity.GoodsCategory;

import java.util.List;


/**
 * Created by ike on 16-11-4.
 */
public interface IGoodsCategorySer extends IService<GoodsCategory, GoodsCategoryDto> {

    void addCategory ( GoodsCategory goodsCategory) throws SerException;

    void updateCategory( GoodsCategory goodsCategory ) throws SerException;

    void deleteCategory( GoodsCategory goodsCategory ) throws SerException;

    void findCategoryByFirstCategory (String firstCategoryName ) throws  SerException;
}
