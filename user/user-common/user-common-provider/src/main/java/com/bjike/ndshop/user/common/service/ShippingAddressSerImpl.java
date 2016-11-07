package com.bjike.ndshop.user.common.service;

import com.bjike.ndshop.dbs.jpa.dto.Condition;
import com.bjike.ndshop.dbs.jpa.enums.DataType;
import com.bjike.ndshop.dbs.jpa.enums.RestrictionType;
import com.bjike.ndshop.dbs.jpa.exception.SerException;
import com.bjike.ndshop.dbs.jpa.service.ServiceImpl;
import com.bjike.ndshop.user.common.dto.ShippingAddressDto;
import com.bjike.ndshop.user.common.entity.ShippingAddress;
import com.bjike.ndshop.user.common.entity.User;
import com.bjike.ndshop.user.common.entity.UserDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by lgq on 16-10-28.
 */
@Service
public class ShippingAddressSerImpl extends ServiceImpl<ShippingAddress, ShippingAddressDto> implements IShippingAddressSer {

    @Override
    public List<ShippingAddress> findAddressByCurrentUser() throws SerException {
        //获取当前用户
        User currentUser = new User();
        //temp
        currentUser.setId("ae57f645-8e1d-4e46-9713-5c78299fa28a");
        ShippingAddressDto dto = new ShippingAddressDto();

        Condition condition = new Condition(User.class.getName()+"#id", DataType.STRING,currentUser.getId());
        condition.setRestrict(RestrictionType.LIKE);
        dto.getConditions().add(condition);
        System.out.println(this.countByCis(dto));
        return  this.findByCis(dto);
    }

    @Transactional
    @Override
    public ShippingAddress addShippingAddress(ShippingAddress address) throws SerException {
        ShippingAddressDto dto = new ShippingAddressDto();
        if(21>count(dto)){
            String seq = findByMaxField("seq",ShippingAddress.class);
            address.setSeq(Integer.parseInt(seq)+1);
            return super.save(address);
        }else {
            throw new SerException("收货地址不能超过20个，请先删除再添加");
        }
    }


    @Transactional
    @Override
    public void setDefaultAddress(ShippingAddress address) throws SerException{
        ShippingAddressDto dto = new ShippingAddressDto();
        Condition condition = new Condition("defaultAddress", DataType.BOOLEAN,true);
        condition.setRestrict(RestrictionType.EQ);
        dto.getConditions().add(condition);
        ShippingAddress old_address = findOne(dto);
        //撤销旧的收货地址
        if(null!=old_address){
            old_address.setDefaultAddress(false);
            update(old_address);
        }
        //设置新的收货地址
        ShippingAddress new_address = findById(address.getId());
        new_address.setDefaultAddress(true);
        update(new_address);
    }



}
