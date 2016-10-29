package com.bjike.ndshop.user.service;

import com.bjike.ndshop.user.dao.IShippingAddressRep;
import com.dounine.corgi.jpa.exception.SerException;
import com.dounine.corgi.jpa.service.ServiceImpl;
import com.bjike.ndshop.user.dto.ShippingAddressDto;
import com.bjike.ndshop.user.entity.ShippingAddress;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by lgq on 16-10-28.
 */
public class ShippingAddressSerImpl extends ServiceImpl<ShippingAddress, ShippingAddressDto> implements IShippingAddressSer {

    @Override
    public ShippingAddress save(ShippingAddress entity) throws SerException {
        ShippingAddressDto dto = new ShippingAddressDto();
        if(20>=count(dto)){
            String seq = findByMaxField("seq",ShippingAddress.class);
            entity.setSeq(Integer.parseInt(seq));
            return super.save(entity);
        }else {
            throw new SerException("收货地址不能超过20个，请先删除再添加");
        }
    }
}
