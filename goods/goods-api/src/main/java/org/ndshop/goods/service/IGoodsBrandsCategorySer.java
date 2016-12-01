package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsBrandsCategoryDto;
import org.ndshop.goods.entity.GoodsBrandsCategory;


/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品品牌分类接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsBrandsCategorySer extends IService<GoodsBrandsCategory, GoodsBrandsCategoryDto> {

    default GoodsBrandsCategory addBrandsCategory(GoodsBrandsCategory goodsBrandsCategory ) throws SerException {return null;};
}