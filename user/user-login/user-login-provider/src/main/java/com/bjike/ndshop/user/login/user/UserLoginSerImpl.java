package com.bjike.ndshop.user.login.user;


import com.bjike.ndshop.user.common.entity.User;
import com.bjike.ndshop.user.common.service.IUserSer;
import com.bjike.ndshop.user.login.service.IUserLoginSer;
import com.bjike.ndshop.user.login.session.TokenUtils;
import com.bjike.ndshop.user.login.session.UserSession;
import com.dounine.corgi.jpa.dto.PageDto;
import com.dounine.corgi.jpa.exception.SerException;
import com.dounine.corgi.rpc.spring.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by huanghuanlai on 16/5/24.
 */
@Service(version = "1.0.0")
public class UserLoginSerImpl implements IUserLoginSer {

    @Autowired
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
        if (null != user) {
            userSer.findByUsernameAndPassword(user.getUsername(),user.getPassword() );

            if ("admin".equals(user.getUsername()) && "admin".equals(user.getPassword())) {
                UserSession.removeByUsername(user.getUsername());
                token = TokenUtils.create("192.168.0.1", user.getUsername());
                UserSession.put(token, user);
            }
        }
        return token;
    }

}
