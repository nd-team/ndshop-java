package org.ndshop.user.register.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.service.IUserSer;
import org.ndshop.user.common.utils.Validator;
import org.ndshop.user.register.dto.UserRegisterDto;
import org.ndshop.user.register.quartz.VerifyCode;
import org.ndshop.user.register.quartz.VerifyQuartz;
import com.dounine.corgi.security.PasswordHash;
import com.dounine.corgi.spring.rpc.Reference;
import com.dounine.corgi.spring.rpc.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: 用户注册业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class UserRegisterSerImpl implements IUserRegisterSer {

    @Reference
    private IUserSer userSer;

    @Cacheable("userSerCache")
    @Override
    public Boolean existUsername(String username) throws SerException {
        Optional<User> optional = userSer.findByUsername(username);
        return optional.isPresent();

    }

    @Cacheable("userSerCache")
    @Override
    public Boolean existPhone(String phone) throws SerException {
        boolean isPhone = Validator.isPhone(phone);
        User user = null;
        if (isPhone) {
            Optional<User> op_user = userSer.findByPhone(phone);
            if (op_user.isPresent()) {
                user = op_user.get();
            }
        } else {
            throw new SerException("手机格式不正确");
        }
        return null != user;

    }


    @Override
    public void sendCodeToPhone(UserRegisterDto dto) throws SerException {
        String phone = dto.getPhone();

        if (userSer.findByPhone(phone).isPresent()) {
            //generateCode()
            String code = "123456";
            VerifyCode verifyCode = new VerifyCode(code);
            VerifyQuartz.put("13457910241", verifyCode);
            //sendToPhone（）
        } else {
            throw new SerException("该手机号码已注册！");

        }
    }

    @Transactional
    @Override
    public void verifyCodeAndReg(UserRegisterDto dto) throws SerException {

        if (dto.getPassword().equals(dto.getRePassword())) {
            if (!Validator.isPassword(dto.getPassword())) {
                throw new SerException("密码过于简单");
            }
        } else {
            throw new SerException("输入密码不一致");
        }

        //通过手机号码获得系统生成的验证码对象
        VerifyCode verifyCode = VerifyQuartz.get(dto.getPhone());
        if (null != verifyCode) {
            if (verifyCode.getCode().equals(dto.getPhoneCode())) {
                saveUserByDto(dto);
                VerifyQuartz.remove(dto.getPhone());
            } else {
                throw new SerException("验证码不正确");
            }

        } else {
            throw new SerException("验证码已过期");
        }


    }

    /**
     * 通过用户注册数据传输实体保存用户
     *
     * @param dto
     * @throws SerException
     */
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
