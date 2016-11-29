package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsDto;
import org.ndshop.goods.entity.Goods;

import java.util.List;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-26 17:19]
 * @Description: [商品接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsSer extends IService<Goods, GoodsDto> {

    default void addGoods(Goods goods , String brandId ,String categoryId ) throws SerException{};

    default Goods findGoodsById() throws SerException { return null;};

    default List<Goods> findByBrandName(String brandName )throws SerException{return null;};
}
