package com.bjike.ndshop.goods.dao;

import com.bjike.ndshop.dbs.jpa.dao.MyRep;
import com.bjike.ndshop.goods.dto.GoodsDto;
import com.bjike.ndshop.goods.entity.Goods;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by ike on 16-11-4.
 */
public interface IGoodsRep extends MyRep<Goods,GoodsDto> {
    @Cacheable("daoCache")
    Goods findByGoodsName(String gName);
}
