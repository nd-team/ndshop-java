package org.ndshop.user.login.ctrl;

import com.dounine.corgi.commons.ResponseText;
import com.dounine.corgi.spring.rpc.Reference;
import org.apache.commons.lang3.StringUtils;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.user.login.dto.UserLoginDto;
import org.ndshop.user.login.service.IUserLoginSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户登录]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RestController
@RequestMapping("login")
public class UserLoginCtrl {

    @Reference
    private IUserLoginSer userLoginSer;

    @GetMapping("login")
    public ResponseText login(UserLoginDto dto, HttpServletRequest request, HttpServletResponse response){
        try {
            String token = userLoginSer.login(dto);
            if(StringUtils.isNotBlank(token)){
//                handlerRememberMe
            }
            return new ResponseText(userLoginSer.login(dto));
        }catch (SerException e){
            return   new ResponseText(e.getMessage());
        }
    }


}
