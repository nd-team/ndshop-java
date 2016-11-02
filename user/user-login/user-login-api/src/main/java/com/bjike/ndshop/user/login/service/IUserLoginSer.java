package com.bjike.ndshop.user.login.service;

import com.bjike.ndshop.user.common.dto.UserDto;
import com.bjike.ndshop.user.common.entity.User;
import com.dounine.corgi.jpa.exception.SerException;
import com.dounine.corgi.jpa.service.IService;
/**
 * Created by lgq on 16-10-31.
 */
public interface IUserLoginSer extends IService<User,UserDto> {

    boolean verify(String token) throws SerException;

    String login(User user) throws SerException;
}
