package com.bjike.ndshop.user.register.service;

import com.bjike.ndshop.user.common.entity.User;
import com.bjike.ndshop.user.register.IUserRegisterSer;
import com.dounine.corgi.jpa.exception.SerException;
import com.dounine.corgi.security.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lgq on 16-10-31.
 */
public class UserRegisterSerImpl implements IUserRegisterSer {
    @Override
    public void register(User user) throws SerException{
        try {
            user.setPassword(PasswordHash.createHash(user.getPassword()));
        }catch (Exception e){
            throw  new SerException(e.getMessage());
        }
        save(user);
    }
}
