package org.ndshop.goods.service;

import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsFieldsDto;
import org.ndshop.goods.dto.GoodsFieldsValueDto;
import org.ndshop.goods.entity.GoodsFields;
import org.ndshop.goods.entity.GoodsFieldsValue;
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


}
