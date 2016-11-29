package org.ndshop.goods.service;

import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsFieldsValueDto;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsFields;
import org.ndshop.goods.entity.GoodsFieldsValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-28 15:10]
 * @Description: [商品扩展字段值业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class GoodsFieldsValueSerImpl extends ServiceImpl<GoodsFieldsValue, GoodsFieldsValueDto> implements IGoodsFieldsValueSer {
    private static Logger logger = Logger.getLogger(GoodsFieldsValueSerImpl.class);
    @Autowired
    private IGoodsFieldsSer goodsFieldsSer;

    @Transactional
    @Override
    public void addGoodsFieldValue(String fieldName , String fieldValue ,String gId ) throws SerException {

        List<GoodsFields> gf = goodsFieldsSer.findGoodsFields(fieldName);

        Condition c = new Condition("id", DataType.STRING, gId);
        c.fieldToModels(Goods.class);
        Condition c2 = new Condition("id", DataType.STRING, gf.get(0).getId());
        c2.fieldToModels(GoodsFields.class);
        GoodsFieldsValueDto dto = new GoodsFieldsValueDto();
        dto.getConditions().add(c);
        dto.getConditions().add(c2);
        List<GoodsFieldsValue> gfvList = findByCis(dto);
        if (gfvList == null || gfvList.size() <= 0) {
            GoodsFieldsValue gfv = new GoodsFieldsValue();
            Goods goods = new Goods();
            goods.setId(gId);
            gfv.setGoods(goods);
            gfv.setGoodsFields(gf.get(0));
            gfv.setFieldValue(fieldValue);
            save(gfv);
        }
    }


    @Transactional
    @Override
    public void updateGoodsFieldValue(String fieldName , String fieldValue ,String gId) throws SerException{
        List<GoodsFields> gf = goodsFieldsSer.findGoodsFields( fieldName );

        Condition c = new Condition("id", DataType.STRING,gId);
        c.fieldToModels( Goods.class );
        Condition c2 = new Condition("id",DataType.STRING,gf.get(0).getId());
        c2.fieldToModels( GoodsFields.class);
        GoodsFieldsValueDto dto = new GoodsFieldsValueDto();
        dto.getConditions().add( c );
        dto.getConditions().add( c2 );
        GoodsFieldsValue gfvList = findOne( dto );
        if( gfvList != null  ){
            gfvList.setFieldValue( fieldValue );
            gfvList.setCreateTime( gfvList.getCreateTime() );
            gfvList.setModifyTime( LocalDateTime.now() );
            update( gfvList  );
        }

    }

    @Cacheable("goodsServiceCache")
    @Override
    public List<GoodsFieldsValue> findFieldVaues(String goodsId) throws SerException{
        String gId = "2a27c9d2-5536-478d-982e-0645731e4679";
        Condition c = new Condition("id", DataType.STRING,gId);
        c.fieldToModels( Goods.class );
        GoodsFieldsValueDto dto = new GoodsFieldsValueDto();
        dto.getConditions().add( c );
        List<GoodsFieldsValue> gfvList = findByCis( dto );
        return  gfvList;
    }
}
