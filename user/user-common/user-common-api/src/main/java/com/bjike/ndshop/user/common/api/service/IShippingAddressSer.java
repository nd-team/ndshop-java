package com.bjike.ndshop.user.common.api.service;

import com.dounine.corgi.jpa.exception.SerException;
import com.dounine.corgi.jpa.service.IService;
import com.bjike.ndshop.user.common.api.dto.ShippingAddressDto;
import com.bjike.ndshop.user.common.api.entity.ShippingAddress;

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
    List<ShippingAddress> findAllAddress() throws SerException;

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
