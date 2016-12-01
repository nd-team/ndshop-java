package org.ndshop.goods.service;

import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsCollectionDto;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsCollection;
import org.ndshop.user.common.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-30 15:04]
 * @Description: [商品收藏业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class GoodsCollectionSerImpl extends ServiceImpl<GoodsCollection, GoodsCollectionDto> implements IGoodsCollectionSer {
    private static Logger logger = Logger.getLogger(GoodsCollectionSerImpl.class);

    @Transactional
    @Override
    public GoodsCollection addGoodsCollection( GoodsCollectionDto goodsCollectionDto)throws SerException {

        User user = new User();
        user.setId( goodsCollectionDto.getUserId() );

        Goods goods = new Goods();
        goods.setId( goodsCollectionDto.getGoodsId() );

        GoodsCollection goodsCollection = new GoodsCollection();
        goodsCollection.setUser( user );
        goodsCollection.setGoods( goods );
        goodsCollection.setCreateTime( LocalDateTime.now() );
        goodsCollection.setModifyTime( LocalDateTime.now() );

        return this.save( goodsCollection );
    }

}
