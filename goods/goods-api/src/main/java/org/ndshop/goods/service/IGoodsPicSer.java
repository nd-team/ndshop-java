package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsPicDto;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsPic;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品图片接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsPicSer extends IService<GoodsPic, GoodsPicDto> {

    /**
     * 添加商品图片
     * @param goods 商品
     * @param picStrs 图片地址
     * @param flag 图片类型
     * @throws SerException
     */
    void addGoodsPic (Goods goods , String picStrs , String flag)throws SerException;

    /**
     * 查找商品图片
     * @param goodsId 商品id
     * @throws SerException
     */
    void findGoodsPic( String goodsId ) throws  SerException;
}
