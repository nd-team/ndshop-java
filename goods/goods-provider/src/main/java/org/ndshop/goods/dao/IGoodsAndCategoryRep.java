package org.ndshop.goods.dao;

import org.ndshop.dbs.jpa.dao.MyRep;
import org.ndshop.goods.dto.GoodsAndCategoryDto;
import org.ndshop.goods.entity.GoodsAndCategory;
/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品和分类持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsAndCategoryRep extends MyRep<GoodsAndCategory,GoodsAndCategoryDto> {
}
