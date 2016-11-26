package org.ndshop.user.login.service;

import org.ndshop.dbs.jpa.exception.SerException;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-26 09:33]
 * @Description: [验证码业务接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IUserAuthCodeSer {


    /**
     * 是否需要验证码
     *
     * @param account 账号
     * @return
     * @throws SerException
     */
    Boolean  showAuthCode(String account) throws SerException;

    /**
     * 生成验证码
     *
     * @param account 账号
     * @return
     * @throws SerException
     */
    Boolean generateCode(String account) throws SerException;
}
