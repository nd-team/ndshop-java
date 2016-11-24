package org.ndshop.user.login.user;


import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.service.IUserSer;
import org.ndshop.user.login.dto.UserLoginDto;
import org.ndshop.user.login.service.IUserLoginSer;
import org.ndshop.user.login.session.TokenUtils;
import org.ndshop.user.login.session.UserSession;
import com.dounine.corgi.security.PasswordHash;
import com.dounine.corgi.spring.rpc.Reference;
import com.dounine.corgi.spring.rpc.Service;

import java.util.Optional;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-24 09:37]
 * @Description: [用户登陆业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
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

    @Override
    public String login(UserLoginDto dto) throws SerException {
        String token = null;
        dto.setIp("192.168.0.1");

        Optional<User> op_user = userSer.findByAccountNumber(dto.getAccount()); //通过用户名/手机号/或者邮箱登陆
        if (op_user.isPresent()) {
            User persistUser = op_user.get();
            try {
                if (PasswordHash.validatePassword(dto.getPassword(), persistUser.getPassword())) {
                    UserSession.removeByUsername(persistUser.getUsername());
                    token = TokenUtils.create(dto.getIp(), persistUser.getUsername());
                    UserSession.put(token, persistUser);
                }

            } catch (Exception e) {
                throw new SerException(e.getMessage());
            }
        }

        return token;
    }


    @Override
    public boolean loginOut(String token) throws SerException {
        UserSession.remove(token);
        return true;
    }
}
