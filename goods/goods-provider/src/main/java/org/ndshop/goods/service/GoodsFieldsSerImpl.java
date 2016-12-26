package org.ndshop.goods.service;

import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.dto.Restrict;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsFieldsDto;
import org.ndshop.goods.entity.GoodsFields;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-28 15:10]
 * @Description: [商品扩展字段业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class GoodsFieldsSerImpl extends ServiceImpl<GoodsFields, GoodsFieldsDto> implements IGoodsFieldsSer {
    private static Logger logger = Logger.getLogger(GoodsFieldsSerImpl.class);

    @Transactional
    @Override
    public void addGoodsFields( GoodsFields goodsFields) throws SerException {
        GoodsFields gf = new GoodsFields();
        gf.setFieldName( goodsFields.getFieldName() );
        gf.setFieldType( goodsFields.getFieldType() );
        gf.setFieldDescription( goodsFields.getFieldDescription() );
        gf.setCreateTime( LocalDateTime.now() );
        gf.setModifyTime( LocalDateTime.now() );

        save( gf );

    }

    @Cacheable("goodsServiceCache")
    @Override
    public List<GoodsFields> findGoodsFields ( String fieldName ) throws SerException{
        GoodsFieldsDto dto = new GoodsFieldsDto();
        dto.getConditions().add(Restrict.eq("fieldName",fieldName));
        List<GoodsFields> goodsFields = findByCis( dto  );

        return goodsFields;
    }

}
