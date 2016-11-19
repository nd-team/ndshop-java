package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsPicDto;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsPic;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by ike on 16-11-12.
 */
public interface IGoodsPicSer extends IService<GoodsPic, GoodsPicDto> {

    void addGoodsPic (Goods goods , String picStrs , String flag)throws SerException;

    void findGoodsPic( String goodsId ) throws  SerException;
}
