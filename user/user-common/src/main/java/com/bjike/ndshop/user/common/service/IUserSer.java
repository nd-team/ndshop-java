package com.bjike.ndshop.user.common.service;

import com.bjike.ndshop.user.common.dto.UserDto;
import com.dounine.corgi.jpa.exception.SerException;
import com.dounine.corgi.jpa.service.IService;
import com.bjike.ndshop.user.common.entity.User;

/**
 * Created by lgq on 16-10-28.
 */
public interface IUserSer extends IService<User, UserDto> {
    
    void register(User user) throws SerException;
    
    User findByUsernameAndPassword(String username,String password);

    User findByUsername(String username);

    User findByNickname(String nickname);
}
