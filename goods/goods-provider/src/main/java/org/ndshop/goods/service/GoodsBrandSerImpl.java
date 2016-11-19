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

/**
 * Created by ike on 16-11-14.
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
        GoodsBrand goodsBrand = findById( gb.getId() );
        if (goodsBrand != null ){
            if( !gb.getBrandName().trim().equals( goodsBrand.getBrandName())){
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
