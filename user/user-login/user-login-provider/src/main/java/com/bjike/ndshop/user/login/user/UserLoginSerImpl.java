package com.bjike.ndshop.user.login.user;


import com.bjike.ndshop.user.common.api.entity.User;
import com.bjike.ndshop.user.login.service.IUserLoginSer;
import com.dounine.corgi.rpc.spring.annotation.Service;

/**
 * Created by huanghuanlai on 16/5/24.
 */
@Service(version = "1.0.0")
public class UserLoginSerImpl implements IUserLoginSer{

    @Override
    public void login(User user) {

    }
}
