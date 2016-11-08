package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.ShopsDto;
import org.ndshop.goods.entity.Shops;
import org.springframework.stereotype.Service;

/**
 * Created by ike on 16-11-8.
 */
@Service
public class ShopsSerImpl extends ServiceImpl<Shops, ShopsDto> implements IShopsSer{
}
