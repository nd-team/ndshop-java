package com.bjike.ndshop.user.service;

import com.dounine.corgi.jpa.exception.SerException;
import com.dounine.corgi.jpa.service.IService;
import com.bjike.ndshop.user.dto.UserDto;
import com.bjike.ndshop.user.entity.User;

/**
 * Created by lgq on 16-10-28.
 */
public interface IUserSer extends IService<User, UserDto> {
    void register(User user) throws SerException;
}
