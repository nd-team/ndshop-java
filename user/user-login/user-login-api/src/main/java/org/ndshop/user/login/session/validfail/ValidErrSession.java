package org.ndshop.user.login.session.validfail;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-25 17:40]
 * @Description: [密码验证错误记录]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class ValidErrSession {

    private static final Logger CONSOLE = LoggerFactory.getLogger(ValidErrSession.class);
    private static final Map<String, VerifyCode> SESSIONS = new ConcurrentHashMap<>(0);
    private static final RuntimeException ACCOUNT_NOT_NULL = new RuntimeException("账户名不能为空");

    private ValidErrSession() {
    }

    static {
        CONSOLE.info("ValidErrSession start");
        new ValidErrQuartz(SESSIONS);
    }


    /**
     * 通过用户查找是否有验证密码错误次数
     *
     * @param account 账号名
     */
    public static VerifyCode get(String account) {
        if (StringUtils.isNotBlank(account)) {
            return  SESSIONS.get(account);
        } else {
            throw ACCOUNT_NOT_NULL;
        }
    }

    /**
     * 登录密码错误次数统计
     *
     * @param account
     */
    public static void putValidErr(String account) {
        if (StringUtils.isNotBlank(account)) {
            if (SESSIONS.containsKey(account)) {
                VerifyCode verify = SESSIONS.get(account);
                verify.setCount(verify.getCount() + 1);
            }else{
                VerifyCode verifyCode = new VerifyCode();
                verifyCode.setCount(1);
                verifyCode.setCreateTime(LocalDateTime.now());
                SESSIONS.put(account,verifyCode);
            }
        } else {
            throw ACCOUNT_NOT_NULL;
        }
    }

    /**
     * 根据用户名删除验证会话信息
     *
     * @param account 用户名
     */
    public static void remove(String account) {
        if (StringUtils.isNotBlank(account)) {
            SESSIONS.remove(account);
        }
        throw ACCOUNT_NOT_NULL;
    }


}
