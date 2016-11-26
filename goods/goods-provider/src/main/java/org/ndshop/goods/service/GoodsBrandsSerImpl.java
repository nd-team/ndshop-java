package org.ndshop.goods.service;

import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsBrandsDto;
import org.ndshop.goods.entity.GoodsBrands;
import org.springframework.stereotype.Service;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品品牌业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class GoodsBrandsSerImpl extends ServiceImpl<GoodsBrands, GoodsBrandsDto> implements IGoodsBrandsSer {
    private static Logger logger = Logger.getLogger(GoodsBrandsSerImpl.class);



}
