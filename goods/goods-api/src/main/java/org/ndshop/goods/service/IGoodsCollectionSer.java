package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsCollectionDto;
import org.ndshop.goods.entity.GoodsCollection;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-30 14:49]
 * @Description: [商品收藏接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsCollectionSer extends IService<GoodsCollection, GoodsCollectionDto> {

    /**
     * 添加商品收藏 ，使用dto里面的userId和goodsId
     * @param goodsCollectionDto 商品收藏传输数据
     * @return
     * @throws SerException
     */
    default GoodsCollection addGoodsCollection( GoodsCollectionDto goodsCollectionDto)throws SerException {return null;};
}
