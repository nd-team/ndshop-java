package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsCollectionDto;
import org.ndshop.goods.entity.GoodsCollection;

/**
 * Created by ike on 16-11-15.
 */
public interface IGoodsCollectionSer extends IService<GoodsCollection , GoodsCollectionDto> {

    void addCollection(String goodsId, String userId) throws SerException;
}
