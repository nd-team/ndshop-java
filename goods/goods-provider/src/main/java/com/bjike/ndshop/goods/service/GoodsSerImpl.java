package com.bjike.ndshop.goods.service;

import com.bjike.ndshop.dbs.jpa.service.ServiceImpl;
import com.bjike.ndshop.goods.dao.IGoodsRep;
import com.bjike.ndshop.goods.dto.GoodsDto;
import com.bjike.ndshop.goods.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ike on 16-11-4.
 */
@Service
public class GoodsSerImpl extends ServiceImpl<Goods, GoodsDto> implements IGoodsSer{
   @Autowired
   private IGoodsRep goodsRep;

    @Override
    public Goods findByGoodName(String goodNames) {
        Goods goods = goodsRep.findByGoodsName( goodNames );
        return goods;
    }
}
