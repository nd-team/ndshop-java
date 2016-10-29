package com.bjike.ndshop.user.service;

import com.bjike.ndshop.user.dto.UserDto;
import com.bjike.ndshop.user.entity.User;
import com.dounine.corgi.jpa.exception.SerException;
import com.dounine.corgi.jpa.service.ServiceImpl;



/**
 * Created by lgq on 16-10-28.
 */
public class UserSerImpl extends ServiceImpl<User, UserDto> implements IUserSer {
    @Override
    public void register(User user) throws SerException{
        save(user);
    }
}
