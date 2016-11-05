package com.bjike.ndshop.user.common.service;

import com.bjike.ndshop.dbs.jpa.exception.SerException;
import com.bjike.ndshop.dbs.jpa.service.IService;
import com.bjike.ndshop.user.common.dto.ShippingAddressDto;
import com.bjike.ndshop.user.common.entity.ShippingAddress;

import java.util.List;


/**
 * Created by lgq on 16-10-28.
 */
public interface IShippingAddressSer extends IService<ShippingAddress, ShippingAddressDto> {

    /**
     * 查询收货地址
     * @return
     * @throws SerException
     */
    List<ShippingAddress> findAddressByCurrentUser() throws SerException;

    /**
     * 添加收货地址
     * @param address
     * @return
     * @throws SerException
     */
    ShippingAddress addShippingAddress(ShippingAddress address) throws SerException;
    /**
     * 设置默认收货地址
      * @param address
     */
    void setDefaultAddress(ShippingAddress address) throws SerException;

}
