package com.bjike.ndshop.user.common.service;

import com.bjike.ndshop.dbs.jpa.dto.Condition;
import com.bjike.ndshop.dbs.jpa.enums.DataType;
import com.bjike.ndshop.dbs.jpa.enums.RestrictionType;
import com.bjike.ndshop.dbs.jpa.service.ServiceImpl;
import com.bjike.ndshop.user.common.dao.IUserRep;
import com.bjike.ndshop.user.common.dto.UserDto;
import com.bjike.ndshop.user.common.entity.User;
import com.bjike.ndshop.dbs.jpa.exception.SerException;
import com.dounine.corgi.spring.rpc.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by lgq on 16-10-28.
 */
@Service
public class UserSerImpl extends ServiceImpl<User, UserDto> implements IUserSer {
    @Autowired
    private IUserRep userRep;


    @Override
    public User findByUsername(String username) {
        return userRep.findByUsername(username);
    }

    @Override
    public User findByNickname(String nickname) {
        return userRep.findByUsername(nickname);
    }

    @Override
    public User findByPhone(String phone) {
        return userRep.findByUsername(phone);
    }

    @Override
    public User findByAccountNumber(String accountNumber) throws SerException {
        UserDto dto = new UserDto();
        List<Condition> conditions = dto.getConditions();

        Condition coin = new Condition("username", DataType.STRING, accountNumber);
        coin.setRestrict(RestrictionType.OR);
        conditions.add(coin);

        coin = new Condition("phone", DataType.STRING, accountNumber);
        coin.setRestrict(RestrictionType.OR);
        conditions.add(coin);

        coin = new Condition("email", DataType.STRING, accountNumber);
        coin.setRestrict(RestrictionType.OR);
        conditions.add(coin);
        return findOne(dto);
    }
}
