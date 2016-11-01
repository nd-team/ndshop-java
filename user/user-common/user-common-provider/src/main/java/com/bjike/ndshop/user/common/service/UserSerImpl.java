package com.bjike.ndshop.user.common.service;

import com.bjike.ndshop.user.common.dao.IUserRep;
import com.bjike.ndshop.user.common.dto.UserDto;
import com.bjike.ndshop.user.common.entity.User;
import com.bjike.ndshop.user.common.service.IUserSer;
import com.dounine.corgi.jpa.exception.SerException;
import com.dounine.corgi.jpa.service.ServiceImpl;
import com.dounine.corgi.security.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by lgq on 16-10-28.
 */
@Service
public class UserSerImpl extends ServiceImpl<User, UserDto> implements IUserSer {
    @Autowired
    private IUserRep userRep;

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRep.findByUsernameAndPassword(username, password);
    }

    @Override
    public User findByUsername(String username) {
        return userRep.findByUsername(username);
    }

    @Override
    public User findByNickname(String nickname) {
        return userRep.findByUsername(nickname);
    }
}
