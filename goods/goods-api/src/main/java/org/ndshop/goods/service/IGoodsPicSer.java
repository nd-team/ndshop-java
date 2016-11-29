package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsPicDto;
import org.ndshop.goods.entity.GoodsPic;

import java.util.List;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-26 17:19]
 * @Description: [商品图片接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsPicSer extends IService<GoodsPic, GoodsPicDto> {

    /**
     * 添加商品图片
     * @param goodsPic 商品图片信息
     * @throws SerException
     */
    default void addPic( GoodsPic goodsPic ) throws SerException {};

    /**
     * 根据商品id 查找商品图片信息
     * @param goodsId
     * @return
     * @throws SerException
     */
    default List<GoodsPic> findPicByGoods(String goodsId ) throws SerException{return null;};
}
