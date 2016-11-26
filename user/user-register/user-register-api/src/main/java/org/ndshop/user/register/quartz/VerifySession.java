package org.ndshop.user.register.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-25 11:32]
 * @Description: [用户注册验证码session管理]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class VerifySession {
    private final static Map<String, VerifyCode> VERIFY_CODE_SESSION = new ConcurrentHashMap<String, VerifyCode>();//time主属
    private static final Logger CONSOLE = LoggerFactory.getLogger(VerifyQuartz.class);

    static {
        CONSOLE.info("VerifyQuartz start");
        new VerifyQuartz(VERIFY_CODE_SESSION);
    }

    public static VerifyCode get(String key) {
        Object obj = VERIFY_CODE_SESSION.get(key);
        if (null != obj) {
            return (VerifyCode) obj;
        } else {
            return null;
        }

    }

    public static boolean remove(String key) {
        if (VERIFY_CODE_SESSION.containsKey(key)) {
            VERIFY_CODE_SESSION.remove(key);
            return true;
        } else {
            return false;
        }
    }

    // key code  time
    public static void put(String key, VerifyCode value) {
        VERIFY_CODE_SESSION.put(key, value);
    }




}
