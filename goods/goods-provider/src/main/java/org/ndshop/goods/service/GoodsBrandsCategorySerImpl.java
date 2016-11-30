package org.ndshop.goods.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsBrandsCategoryDto;
import org.ndshop.goods.entity.GoodsBrandsCategory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public GoodsBrandsCategory addBrandsCategory(GoodsBrandsCategory goodsBrandsCategory ) throws SerException {

        GoodsBrandsCategory goodsBrandsCategoryNew = new GoodsBrandsCategory();
        if(StringUtils.isNotBlank( goodsBrandsCategory.getName() )){
            BeanUtils.copyProperties(goodsBrandsCategory , goodsBrandsCategoryNew ,goodsBrandsCategory.getId() );
            save(goodsBrandsCategoryNew);
        }
        return goodsBrandsCategoryNew;
    }


}
