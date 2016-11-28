package org.ndshop.goods.dao;

import org.ndshop.dbs.jpa.dao.MyRep;
import org.ndshop.goods.dto.GoodsBrandsDto;
import org.ndshop.goods.entity.GoodsBrands;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品品牌持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsBrandsRep extends MyRep<GoodsBrands,GoodsBrandsDto> {
}
