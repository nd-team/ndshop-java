package org.ndshop.goods.service;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsBrandDto;
import org.ndshop.goods.entity.GoodsBrand;
import org.ndshop.goods.enums.BrandStatus;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品品牌业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class GoodsBrandSerImpl extends ServiceImpl<GoodsBrand, GoodsBrandDto> implements IGoodsBrandSer {
    private static Logger logger = Logger.getLogger(GoodsBrandSerImpl.class);

    @Transactional
    @Override
    public void addBrand(String brandName ) throws SerException {
        GoodsBrand goodsBrand = new GoodsBrand();
        goodsBrand.setBrandName(  brandName );
        goodsBrand.setBrandStatus(BrandStatus.FROZEN.getName() );
        goodsBrand.setCreateTime(  LocalDateTime.now() );
        goodsBrand.setModifyTime(  LocalDateTime.now() );
        save( goodsBrand );
    }

    @Transactional
    @Override
    public void updateBrand (GoodsBrand gb )throws SerException{
        Optional<GoodsBrand> opGoodsBrand = findById( gb.getId() );
        if (opGoodsBrand.isPresent() ){
            if( !gb.getBrandName().trim().equals( opGoodsBrand.get().getBrandName())){
                goodsBrand.setBrandName( gb.getBrandName().trim() );
            }
            if( !gb.getBrandStatus().trim().equals( goodsBrand.getBrandStatus())){
                goodsBrand.setBrandStatus(  gb.getBrandStatus().trim() );
            }
            goodsBrand.setModifyTime( LocalDateTime.now() );
            update( goodsBrand );
        }
        logger.info( JSON.toJSONString (goodsBrand) );
    }

    @Cacheable("goodsServiceCache")
    @Override
    public void findBrand() throws SerException{
        List<GoodsBrand> goodsBrands = findAll();
        logger.info( JSON.toJSONString( goodsBrands));
    }
}
