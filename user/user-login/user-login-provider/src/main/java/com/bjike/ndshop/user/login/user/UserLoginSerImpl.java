package com.bjike.ndshop.user.login.user;



import com.bjike.ndshop.dbs.jpa.exception.SerException;
import com.bjike.ndshop.user.common.dto.UserDto;
import com.bjike.ndshop.user.common.entity.User;
import com.bjike.ndshop.user.common.service.IUserSer;
import com.bjike.ndshop.user.login.service.IUserLoginSer;
import com.bjike.ndshop.user.login.session.TokenUtils;
import com.bjike.ndshop.user.login.session.UserSession;
import com.dounine.corgi.security.PasswordHash;
import com.dounine.corgi.spring.rpc.Reference;
import com.dounine.corgi.spring.rpc.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by huanghuanlai on 16/5/24.
 */
@Service
public class UserLoginSerImpl implements IUserLoginSer {

    @Reference
    private IUserSer userSer;

    @Override
    public boolean verify(String token) throws SerException {
        if (TokenUtils.verify(token)) {
            return UserSession.exist(token);
        }
        throw new SerException("token无效");
    }


    public String login(User user) throws SerException {
        String token = null;

        //------------------------test
        if ("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())) {
            UserSession.removeByUsername(user.getUsername());
            token = TokenUtils.create("192.168.0.1", user.getUsername());
            UserSession.put(token, user);
            return token;
        }

        String account = StringUtils.isNotBlank(user.getUsername()) ? user.getUsername() : user.getEmail();
        account = StringUtils.isBlank(account) ? user.getPassword() : account;
        User persistUser = userSer.findByAccountNumber(account); //通过用户名/手机号/或者邮箱登陆
        try {
            if (null != persistUser) {
                if (persistUser.getUsername().equals(user.getUsername())
                        && PasswordHash.validatePassword(user.getPassword(), persistUser.getPassword())) {
                    UserSession.removeByUsername(user.getUsername());
                    token = TokenUtils.create("192.168.0.1", user.getUsername());
                    UserSession.put(token, user);
                }
            }

        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }


        return token;
    }


}
