package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsAndCategoryDto;
import org.ndshop.goods.entity.GoodsAndCategory;
import org.springframework.stereotype.Service;

/**
 * Created by tanghaixiang on 16-11-23.
 */
@Service
public class GoodsAndCategorySerImpl extends ServiceImpl<GoodsAndCategory,GoodsAndCategoryDto> implements IGoodsAndCategorySer{
}
