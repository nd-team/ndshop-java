package com.bjike.ndshop.user.common.api.service;

import com.bjike.ndshop.user.common.api.dto.UserDto;
import com.dounine.corgi.jpa.exception.SerException;
import com.dounine.corgi.jpa.service.IService;
import com.bjike.ndshop.user.common.api.entity.User;

/**
 * Created by lgq on 16-10-28.
 */
public interface IUserSer extends IService<User, UserDto> {

    default User findByUsernameAndPassword(String username,String password){
        return null;
    }

    default User findByUsername(String username){
        return null;
    }

    default User findByNickname(String nickname){
        return null;
    }
}
