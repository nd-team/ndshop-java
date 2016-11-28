package org.ndshop.goods.dao;

import org.ndshop.dbs.jpa.dao.MyRep;
import org.ndshop.goods.dto.GoodsFieldsValueDto;
import org.ndshop.goods.entity.GoodsFieldsValue;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-28 15:03]
 * @Description: [商品扩展字段值持久化接口, 继承基类可使用ｊｐａ命名查询]]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsFieldsValueRep extends MyRep<GoodsFieldsValue,GoodsFieldsValueDto> {
}
