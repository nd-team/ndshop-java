package org.ndshop.goods.service;

import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsBrandsDto;
import org.ndshop.goods.entity.GoodsBrands;
import org.ndshop.goods.entity.GoodsBrandsCategory;
import org.ndshop.goods.enums.GoodsBrandsRecommendStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

    @Transactional
    @Override
    public GoodsBrands addBrands( GoodsBrands goodsBrands ,GoodsBrandsDto goodsBrandsDto ) throws SerException{
        GoodsBrands goodsBrandsNew = new GoodsBrands();
        goodsBrandsNew.setName( goodsBrands.getName() );
        goodsBrandsNew.setTrademark( goodsBrands.getTrademark() );
        goodsBrandsNew.setGoodsBrandsRecommendStatus(GoodsBrandsRecommendStatus.NOTRECOMMEND );
        goodsBrandsNew.setKeyWord( goodsBrands.getKeyWord() );
        goodsBrandsNew.setCreateTime( LocalDateTime.now() );
        goodsBrandsNew.setModifyTime( LocalDateTime.now() );

        GoodsBrandsCategory goodsBrandsCategory = new GoodsBrandsCategory();
        goodsBrandsCategory.setId( goodsBrandsDto.getGoodsBrandsCategoryId() );
        goodsBrandsNew.setGoodsBrandsCategory(  goodsBrandsCategory );

        return this.save( goodsBrandsNew );
    }


}
