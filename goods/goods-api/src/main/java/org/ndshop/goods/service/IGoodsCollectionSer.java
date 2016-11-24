package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsCollectionDto;
import org.ndshop.goods.entity.GoodsCollection;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品收藏接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsCollectionSer extends IService<GoodsCollection , GoodsCollectionDto> {

    /**
     * 添加收藏
     * @param goodsId  商品id
     * @param userId  用户id
     * @throws SerException
     */
    void addCollection(String goodsId, String userId) throws SerException;

    /**
     * 更新收藏
     * @param goodsId 商品id
     * @param userId 用户id
     * @throws SerException
     */
    void updateCollection(String goodsId ,String userId ) throws SerException;

    /**
     * 查找分类
     * @param userId  用户id
     * @throws SerException
     */
    void findCollection(String userId ) throws SerException;
}
