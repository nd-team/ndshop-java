package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsFieldsDto;
import org.ndshop.goods.entity.GoodsFields;

import java.util.List;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-28 15:00]
 * @Description: [商品扩展字段接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsFieldsSer extends IService<GoodsFields, GoodsFieldsDto> {

    /**
     * 添加商品扩展字段
     * @param goodsFields 商品扩展字段
     * @throws SerException
     */
    default void addGoodsFields( GoodsFields goodsFields) throws SerException {};

    /**
     * 根据扩展字段名查找
     * @param fieldName
     * @return
     * @throws SerException
     */
    default List<GoodsFields> findGoodsFields (String fieldName ) throws SerException{return null;};

}
