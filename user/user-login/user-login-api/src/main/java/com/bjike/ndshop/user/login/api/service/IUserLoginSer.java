package com.bjike.ndshop.user.login.api.service;

import com.bjike.ndshop.user.common.api.dto.UserDto;
import com.bjike.ndshop.user.common.api.entity.User;
import com.dounine.corgi.jpa.service.IService;
/**
 * Created by lgq on 16-10-31.
 */
public interface IUserLoginSer extends IService<User,UserDto> {
    void login(User user);
}
