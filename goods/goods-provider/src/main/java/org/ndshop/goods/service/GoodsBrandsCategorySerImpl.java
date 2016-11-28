package org.ndshop.goods.service;

import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsBrandsCategoryDto;
import org.ndshop.goods.entity.GoodsBrandsCategory;
import org.springframework.stereotype.Service;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品品牌分类业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class GoodsBrandsCategorySerImpl extends ServiceImpl<GoodsBrandsCategory, GoodsBrandsCategoryDto> implements IGoodsBrandsCategorySer {
    private static Logger logger = Logger.getLogger(GoodsBrandsCategorySerImpl.class);


}
