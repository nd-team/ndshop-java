package org.ndshop.user.common.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.user.common.dto.ShippingAddressDto;
import org.ndshop.user.common.entity.ShippingAddress;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户收货地址业务接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IShippingAddressSer extends IService<ShippingAddress, ShippingAddressDto> {

    /**
     * 通过当前用户查询收货地址
     *
     * @return
     * @throws SerException
     */
    List<ShippingAddress> findAddressByCurrentUser() throws SerException;

    /**
     * 添加收货地址
     *
     * @param address 收货地址实体
     * @return
     * @throws SerException
     */
    ShippingAddress addShippingAddress(ShippingAddress address) throws SerException;

    /**
     * 设置默认收货地址
     *
     * @param address 收货地址实体
     * @throws SerException
     */
    void setDefaultAddress(ShippingAddress address) throws SerException;

}
