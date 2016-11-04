package com.bjike.ndshop.user.register.service;

import com.bjike.ndshop.dbs.jpa.exception.SerException;
import com.bjike.ndshop.user.common.entity.User;
import com.bjike.ndshop.user.common.service.IUserSer;
import com.bjike.ndshop.user.register.dto.UserRegisterDto;
import com.dounine.corgi.rpc.spring.annotation.Autowired;
import com.dounine.corgi.rpc.spring.annotation.Service;
import com.dounine.corgi.security.PasswordHash;

import java.util.regex.Pattern;

/**
 * Created by lgq on 16-10-31.
 */
@Service
public class UserRegisterSerImpl  implements IUserRegisterSer {
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    public static final String REGEX_PASSWORD = "^(?!\\d+$)(?![a-zA-Z]+$)[a-zA-Z\\d]+$";


    @Autowired
    private IUserSer userSer;

    @Override
    public Boolean existUsername(String username) throws SerException {
        User user = userSer.findByUsername(username);
        return null != user;

    }

    @Override
    public Boolean existPhone(String phone) throws SerException {
         User user = userSer.findByPhone(phone);
        return null != user;

    }

    @Override
    public void verifyCodeToPhone(UserRegisterDto dto) throws SerException {
        String phone = dto.getPhone();
        boolean reg = Pattern.matches(REGEX_MOBILE, phone);
        if (reg) {
            if (null == userSer.findByPhone(phone)) {
                //generateCode()
                //sendToPhone（）

            } else {
                throw new SerException("该手机号码已注册！");

            }
        } else {
            throw new SerException("手机格式不正确");
        }
    }

    @Override
    public void verifyCodeAndReg(UserRegisterDto dto) throws SerException {
        if (!dto.getPhone_code().equals("123")) {
            throw new SerException("验证码不正确");
        }
        if ("code".equals("timeout")) {
            throw new SerException("验证码已过期");
        }

        if (dto.getPassword().equals(dto.getRePassword())) {
            if (!Pattern.matches(dto.getPassword(), REGEX_PASSWORD)) {
                throw new SerException("密码过于简单");
            }
        } else {
            throw new SerException("输入密码不一致");
        }
        saveUserByDto(dto);
    }

    private void saveUserByDto(UserRegisterDto dto) throws SerException {
        try {
            User user = new User();
            user.setUsername(dto.getUsername());
            user.setPassword(PasswordHash.createHash(dto.getPassword()));
            user.setPhone(dto.getPhone());
            userSer.save(user);
        } catch (Exception e) {
            throw new SerException("密码加密错误");
        }
    }


}
