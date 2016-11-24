package org.ndshop.goods.dao;

import org.ndshop.dbs.jpa.dao.MyRep;
import org.ndshop.goods.dto.GoodsDto;
import org.ndshop.goods.entity.Goods;
import org.springframework.cache.annotation.Cacheable;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品持久化接口, 继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsRep extends MyRep<Goods,GoodsDto> {
    /**
     * 根据商品名查找商品
     * @param goodsName 商品名
     * @return
     */
    @Cacheable("goodsDaoCache")
    Goods findByGoodsName(String goodsName);

//    @Cacheable("daoCache")
//    List<Goods> findByFirstCategoryAndSecondCategory (String firstCategory , String secondCategory);
}
