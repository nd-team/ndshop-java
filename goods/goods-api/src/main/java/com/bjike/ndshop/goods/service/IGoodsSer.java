package com.bjike.ndshop.goods.service;

import com.bjike.ndshop.dbs.jpa.service.IService;
import com.bjike.ndshop.goods.dto.GoodsDto;
import com.bjike.ndshop.goods.entity.Goods;


/**
 * Created by ike on 16-11-4.
 */
public interface IGoodsSer extends IService<Goods, GoodsDto> {
    default Goods findByGoodName(String goodsName){
        return null;
    }
}
