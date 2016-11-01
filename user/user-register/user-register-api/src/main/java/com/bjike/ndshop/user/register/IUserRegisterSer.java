package com.bjike.ndshop.user.register;

import com.bjike.ndshop.user.common.dto.UserDto;
import com.bjike.ndshop.user.common.entity.User;
import com.dounine.corgi.jpa.exception.SerException;
import com.dounine.corgi.jpa.service.IService;

/**
 * Created by lgq on 16-10-31.
 */
public interface IUserRegisterSer extends IService<User,UserDto> {
    void register(User user)throws SerException;
}
