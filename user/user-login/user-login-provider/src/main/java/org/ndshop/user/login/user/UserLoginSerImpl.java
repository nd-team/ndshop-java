package org.ndshop.user.login.user;


import com.dounine.corgi.security.PasswordHash;
import com.dounine.corgi.spring.rpc.Reference;
import com.dounine.corgi.spring.rpc.Service;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.enums.LoginStatus;
import org.ndshop.user.common.service.IUserSer;
import org.ndshop.user.common.utils.CookieOperate;
import org.ndshop.user.login.dto.UserLoginDto;
import org.ndshop.user.login.service.IUserLoginSer;
import org.ndshop.user.login.session.validcorrect.TokenUtils;
import org.ndshop.user.login.session.validcorrect.UserSession;
import org.ndshop.user.login.session.validfail.ValidErrSession;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public Boolean verify(String token) throws SerException {
        if (TokenUtils.verify(token)) {
            return UserSession.exist(token);
        }
        throw new SerException("token无效");
    }

    @Override
    public String login(UserLoginDto dto) throws SerException {

        String token = null;
        dto.setIp("192.168.0.1");
        User user = userSer.findByAccountNumber(dto.getAccount()); //通过用户名/手机号/或者邮箱查找用户
        if (null != user) {
            token = validatePassword(dto, user);  //验证密码
            //  handlerRememberMe(dto,request,response); //处理记住我
            // tokenToCookie(token,request,response); //保存登录令牌到cookie
        }
        return token;
    }

    /**
     * 验证登陆密码
     *
     * @param dto
     */
    private String validatePassword(UserLoginDto dto, User persistUser) throws SerException {
        String token = null;
        String account = dto.getAccount();
        try {
            if (PasswordHash.validatePassword(dto.getPassword(), persistUser.getPassword())) {
                UserSession.removeByUsername(persistUser.getUsername());
                token = TokenUtils.create(dto.getIp(), persistUser.getUsername());
                persistUser.setLoginStatus(LoginStatus.LOGIN);
                UserSession.put(token, persistUser);
                ValidErrSession.remove(account);
                //写入cookie
            } else { //密码错误
                ValidErrSession.putValidErr(account);
            }

        } catch (Exception e) {
            throw new SerException(e.getMessage());
        }
        return token;
    }

    /**
     * 处理记住账号密码
     *
     * @param dto
     */
    private void handlerRememberMe(UserLoginDto dto) {
        if (dto.isRememberMe()) {
            Cookie cookie = new Cookie("account", dto.getAccount());
            cookie.setMaxAge(100);
//            CookieOperate.addCookie(cookie,response);
        } else {
//            CookieOperate.removeCookieByName(dto.getAccount(),request,response);
        }
    }

    private void tokenToCookie(String token, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(100);
        CookieOperate.addCookie(cookie, response);
    }


    @Override
    public Boolean loginOut(String token) throws SerException {
        User user = UserSession.get(token);
        if (null != user) {
            user.setLoginStatus(LoginStatus.LOGINOUT);
        }
        return true;
    }


}
