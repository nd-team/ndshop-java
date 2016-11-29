package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsFieldsValueDto;
import org.ndshop.goods.entity.GoodsFieldsValue;

import java.util.List;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-28 15:00]
 * @Description: [商品扩展字段值接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsFieldsValueSer extends IService<GoodsFieldsValue, GoodsFieldsValueDto> {

    /**
     * 为商品的某个扩展属性添加值
     * @param fieldName  扩展属性名
     * @param fieldValue 扩展属性值
     * @param gId 商品id
     * @throws SerException
     */
    default void addGoodsFieldValue(String fieldName , String fieldValue ,String gId ) throws SerException {};

    /**
     * 修改商品的某个扩展属性的值
     * @param fieldName  扩展属性名
     * @param fieldValue 扩展属性值
     * @param gId 商品id
     * @throws SerException
     */
    default void updateGoodsFieldValue(String fieldName , String fieldValue ,String gId) throws SerException{};

    /**
     * 根据商品id查找商品所有的扩展属性的值
     * @param goodsId 商品id
     * @return
     * @throws SerException
     */
    default List<GoodsFieldsValue> findFieldVaues(String goodsId) throws SerException{return null;};
}
