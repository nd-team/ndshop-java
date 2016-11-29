package org.ndshop.user.login.Interceptor;

import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.enums.LoginStatus;
import org.ndshop.user.common.utils.CookieOperate;
import org.ndshop.user.login.session.validcorrect.UserSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [登录拦截器（验证是否登陆）]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class LoginSecurityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie cookie = CookieOperate.getCookieByName(request,"token");

        if(null!=cookie ){ //已登录
            User user = UserSession.get(cookie.getValue());
            //session 存在用户登录令牌且未退出
            if(null!=user && user.getLoginStatus().equals(LoginStatus.LOGIN)){
                return  true;
            }
        }
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
