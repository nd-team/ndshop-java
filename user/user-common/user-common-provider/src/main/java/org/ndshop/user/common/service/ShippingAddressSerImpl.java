package org.ndshop.user.common.service;

import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.user.common.dto.ShippingAddressDto;
import org.ndshop.user.common.entity.ShippingAddress;
import org.ndshop.user.common.entity.User;
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
        currentUser.setId("11");
        ShippingAddressDto dto = new ShippingAddressDto();
        Condition condition = new Condition("id", DataType.STRING,currentUser.getId());
        condition.setRestrict(RestrictionType.EQ);
        condition.fieldToModels(User.class);
        dto.getConditions().add(condition);
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
