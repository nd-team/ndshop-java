package com.bjike.ndshop.user.commom.provider.user;

import com.bjike.ndshop.user.common.api.dao.IUserRep;
import com.bjike.ndshop.user.common.api.dto.UserDto;
import com.bjike.ndshop.user.common.api.entity.User;
import com.bjike.ndshop.user.common.api.service.IUserSer;
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
