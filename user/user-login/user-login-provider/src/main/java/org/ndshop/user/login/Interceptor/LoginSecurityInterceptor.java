package org.ndshop.user.login.Interceptor;

import org.ndshop.user.common.utils.CookieOperate;
import org.ndshop.user.login.session.UserSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lgq on 16-11-21.
 * 登录拦截器
 */

public class LoginSecurityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie cookie = CookieOperate.getCookieByName(request,"token");
        if(null!=cookie && UserSession.exist(cookie.getValue())){ //已登录
            return  true;
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
