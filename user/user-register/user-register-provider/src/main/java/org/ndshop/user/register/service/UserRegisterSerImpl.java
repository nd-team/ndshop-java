package org.ndshop.user.register.service;

import com.dounine.corgi.security.PasswordHash;
import com.dounine.corgi.spring.rpc.Reference;
import com.dounine.corgi.spring.rpc.Service;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.service.IUserSer;
import org.ndshop.user.common.session.authcode.AuthCode;
import org.ndshop.user.common.session.authcode.AuthCodeSession;
import org.ndshop.user.common.session.phonecode.PhoneCode;
import org.ndshop.user.common.session.phonecode.PhoneCodeSession;
import org.ndshop.user.common.utils.authCode.AuthCodeGenerate;
import org.ndshop.user.common.utils.regex.Validator;
import org.ndshop.user.register.dto.UserRegisterDto;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: 用户注册业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service
public class UserRegisterSerImpl implements IUserRegisterSer {

    @Reference
    private IUserSer userSer;

    @Override
    public BufferedImage generateRegAuthCode(String sid) throws SerException {
        Map<String, BufferedImage> authMap = AuthCodeGenerate.build();
        String code = null;
        BufferedImage image = null;
        for (Map.Entry<String, BufferedImage> entry : authMap.entrySet()) {
            image = entry.getValue();
            code = entry.getKey();
        }
        handleAuthCode(sid, code);
        return image;
    }

    /**
     * 保存注册验证码到session
     *
     * @param sid
     * @param code
     */
    private void handleAuthCode(String sid, String code) {
        AuthCode authCode = new AuthCode();
        authCode.setCode(code);
        AuthCodeSession.put(sid, authCode);
    }

    @Cacheable
    @Override
    public Boolean existUsername(String username) throws SerException {
        User user = userSer.findByUsername(username);
        return null != user;

    }

    @Cacheable
    @Override
    public Boolean existPhone(String phone) throws SerException {
        boolean isPhone = Validator.isPhone(phone);
        User user = null;
        if (isPhone) {
            user = userSer.findByPhone(phone);

        } else {
            throw new SerException("手机格式不正确");
        }
        return null != user;

    }


    @Override
    public void sendCodeToPhone(UserRegisterDto dto) throws SerException {
        String phone = dto.getPhone();

        if (null != userSer.findByPhone(phone)) {
            //generateCode()
            String code = "123456";
            PhoneCode phoneCode = new PhoneCode();
            phoneCode.setCode(code);
            PhoneCodeSession.put("13457910241", phoneCode);
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
        AuthCode authCode = AuthCodeSession.get(dto.getSid());

        if (null == authCode && !dto.getAuthCode().equalsIgnoreCase(authCode.getCode())) {
            throw new SerException("验证码错误");
        }

        //通过手机号码获得系统生成的验证码对象
        PhoneCode phoneCode = PhoneCodeSession.get(dto.getPhone());
        if (null != phoneCode) {
            if (phoneCode.getCode().equalsIgnoreCase(dto.getPhoneCode())) {
                saveUserByDto(dto);
                PhoneCodeSession.remove(dto.getPhone());
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
