package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsDto;
import org.ndshop.goods.entity.Goods;


/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsSer extends IService<Goods, GoodsDto> {
    /**
     * 通过商品名查找商品
     * @param goodsName 商品名
     * @return
     */
    default Goods findByGoodName(String goodsName) {
        return null;
    }


    /**
     * 添加商品
     * @param goods 商品
     * @param shopId 店铺id
     * @param categoryId 分类id
     * @param goodsBrandId 商品品牌id
     * @throws SerException
     */
    default void addGoods(Goods goods, String shopId, String categoryId, String goodsBrandId) throws SerException {
    }

    /**
     * 根据三级分类查找商品
     * @param thirdCagetoryName 三级分类名
     * @throws SerException
     */
    void findGoodsByThirdCategory(String thirdCagetoryName) throws SerException;

    /**
     * 查找商品描述
     * @param goodId 商品id
     * @throws SerException
     */
    void findDese(String goodId) throws SerException;
}
