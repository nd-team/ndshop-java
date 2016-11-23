package org.ndshop.user.common.dao;

import org.ndshop.dbs.jpa.dao.MyRep;
import org.ndshop.user.common.dto.ShippingAddressDto;
import org.ndshop.user.common.entity.ShippingAddress;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [收货地址持久化接口,继承基类可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IShippingAddressRep extends MyRep<ShippingAddress, ShippingAddressDto> {

}
