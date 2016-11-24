package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsAndCategoryDto;
import org.ndshop.goods.entity.GoodsAndCategory;
import org.springframework.stereotype.Service;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品和分类业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class GoodsAndCategorySerImpl extends ServiceImpl<GoodsAndCategory,GoodsAndCategoryDto> implements IGoodsAndCategorySer{
}
