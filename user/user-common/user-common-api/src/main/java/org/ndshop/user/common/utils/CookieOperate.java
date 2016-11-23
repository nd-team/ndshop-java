package org.ndshop.user.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lgq on 16-11-22.
 * 操作cookie
 */
public class CookieOperate {


    public static  void addCookie(Cookie cookie, HttpServletResponse response) {
        if(null!=cookie){
            response.addCookie(cookie);
        }
    }

    public static void removeCookieByName(String name, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (null!=cookies) {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(name)){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);// 立即销毁cookie
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }

    public static Cookie getCookieByName(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                if(name.equals(cookie.getName())){
                    return cookie;
                }
            }
        }
        return null;
    }


}
