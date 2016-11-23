package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsDto;
import org.ndshop.goods.entity.Goods;


/**
 * Created by ike on 16-11-4.
 */
public interface IGoodsSer extends IService<Goods, GoodsDto> {
    default Goods findByGoodName(String goodsName) {
        return null;
    }


    default void addGoods(Goods goods, String shopId, String categoryId, String goodsBrandId) throws SerException {
    }

    void findGoodsByThirdCategory(String thirdCagetoryName) throws SerException;

    void findDese(String goodId) throws SerException;
}
