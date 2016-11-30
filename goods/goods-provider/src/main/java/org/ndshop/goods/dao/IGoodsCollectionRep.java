package org.ndshop.goods.dao;

import org.ndshop.dbs.jpa.dao.MyRep;
import org.ndshop.goods.dto.GoodsCollectionDto;
import org.ndshop.goods.entity.GoodsCollection;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-30 15:02]
 * @Description: [商品收藏持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsCollectionRep extends MyRep<GoodsCollection,GoodsCollectionDto> {
}
