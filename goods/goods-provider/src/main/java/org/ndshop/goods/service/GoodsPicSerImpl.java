package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsPicDto;
import org.ndshop.goods.entity.GoodsPic;
import org.springframework.stereotype.Service;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-26 17:25]
 * @Description: [商品图片业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class GoodsPicSerImpl extends ServiceImpl<GoodsPic, GoodsPicDto> implements IGoodsPicSer {
}
