package org.ndshop.goods.service;

import com.dounine.corgi.spring.rpc.Reference;
import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsCollectionDto;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsCollection;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.service.IUserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by ike on 16-11-15.
 */
@Service
public class GoodsCollectionSerImpl extends ServiceImpl<GoodsCollection, GoodsCollectionDto> implements IGoodsCollectionSer {

    private static Logger logger = Logger.getLogger(GoodsCollectionSerImpl.class);

    @Autowired
    private IGoodsSer goodsSer;
    @Reference
    private IUserSer userSer;

    @Transactional
    @Override
    public void addCollection(String goodsId, String userId) throws SerException {
        GoodsCollection goodsCollection = new GoodsCollection();
        Goods goods = goodsSer.findById(goodsId);
        goodsCollection.setGoods(goods);

        User user = userSer.findById(userId);
        System.out.println(user);
        goodsCollection.setUser(user);

        goodsCollection.setCreateTime(LocalDateTime.now());
        goodsCollection.setModifyTime(LocalDateTime.now());

        save(goodsCollection);
        logger.info(goodsCollection);
    }

}
