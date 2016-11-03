package com.bjike.ndshop.user.register.service;

import com.bjike.ndshop.dbs.jpa.exception.SerException;
import com.bjike.ndshop.dbs.jpa.service.IService;
import com.bjike.ndshop.user.common.dto.UserDto;
import com.bjike.ndshop.user.common.entity.User;
import com.bjike.ndshop.user.register.dto.UserRegisterDto;

/**
 * Created by lgq on 16-10-31.
 */
public interface IUserRegisterSer extends IService<User,UserDto> {
    /**
     * 用户名是否已经注册
     * @param username
     * @throws SerException
     */
    Boolean existUsername(String username)throws SerException;

    /**
     * 手机号是否已注册
     * @param phone
     * @return
     * @throws SerException
     */
    Boolean existPhone(String phone) throws  SerException;

    /**
     * 发送验证码到手机
     */
    void verifyCodeToPhone(UserRegisterDto dto)throws SerException;

    /**
     * 验证并注册
     * @param dto
     * @throws SerException
     */
    void verifyCodeAndReg(UserRegisterDto dto) throws SerException;


}
