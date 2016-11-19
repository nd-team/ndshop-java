package org.ndshop.user.common.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.user.common.dto.ShippingAddressDto;
import org.ndshop.user.common.entity.ShippingAddress;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


/**
 * Created by lgq on 16-10-28.
 */
public interface IShippingAddressSer extends IService<ShippingAddress, ShippingAddressDto> {

    /**
     * 查询收货地址
     *
     * @return
     * @throws SerException
     */
    List<ShippingAddress> findAddressByCurrentUser() throws SerException;

    /**
     * 添加收货地址
     *
     * @param address
     * @return
     * @throws SerException
     */
    ShippingAddress addShippingAddress(ShippingAddress address) throws SerException;

    /**
     * 设置默认收货地址
     *
     * @param address
     */
    void setDefaultAddress(ShippingAddress address) throws SerException;

}
