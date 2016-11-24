package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.ShopsDto;
import org.ndshop.goods.entity.Shops;
import org.springframework.stereotype.Service;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [店铺业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class ShopsSerImpl extends ServiceImpl<Shops, ShopsDto> implements IShopsSer {
}
