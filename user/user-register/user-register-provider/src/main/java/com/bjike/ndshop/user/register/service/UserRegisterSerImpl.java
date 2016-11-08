package com.bjike.ndshop.user.register.service;

import com.bjike.ndshop.dbs.jpa.exception.SerException;
import com.bjike.ndshop.user.common.entity.User;
import com.bjike.ndshop.user.common.service.IUserSer;
import com.bjike.ndshop.user.common.utils.Validator;
import com.bjike.ndshop.user.register.dto.UserRegisterDto;
import com.dounine.corgi.security.PasswordHash;
import com.dounine.corgi.spring.rpc.Reference;
import com.dounine.corgi.spring.rpc.Service;
import java.time.LocalDateTime;

/**
 * Created by lgq on 16-10-31.
 */
@Service
public class UserRegisterSerImpl  implements IUserRegisterSer {

    @Reference
    private IUserSer userSer;

    @Override
    public Boolean existUsername(String username) throws SerException {
        User user = userSer.findByUsername(username);
        return null != user;

    }

    @Override
    public Boolean existPhone(String phone) throws SerException {
        boolean reg = Validator.isPhone(phone);
        User user = null;
        if (reg) {
          user = userSer.findByPhone(phone);
        }else {
            throw new SerException("手机格式不正确");
        }
        return null != user;

    }

    @Override
    public void sendCodeToPhone(UserRegisterDto dto) throws SerException {
        String phone = dto.getPhone();

            if (null == userSer.findByPhone(phone)) {
                //generateCode()
                //sendToPhone（）
            } else {
                throw new SerException("该手机号码已注册！");

            }
    }

    @Override
    public void verifyCodeAndReg(UserRegisterDto dto) throws SerException {

        if (dto.getPassword().equals(dto.getRePassword())) {
            if (!Validator.isPassword(dto.getPassword())) {
                throw new SerException("密码过于简单");
            }
        } else {
            throw new SerException("输入密码不一致");
        }

        if (dto.getPhone_code().equals("123")) {
            LocalDateTime databaseTime = LocalDateTime.now(); //session或者数据库保存的验证码时间
            if (!LocalDateTime.now().isBefore(databaseTime.plusMinutes(15))) {
                throw new SerException("验证码已过期");
            }
        }else{
            throw new SerException("验证码不正确");
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
            throw new SerException(e.getMessage());
        }
    }


}
