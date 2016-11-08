package org.ndshop.user.register.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lgq on 16-11-8.
 * 验证码定时失效
 */
public class VerifyQuartz {
    private final static Map<String, VerifyCode> VERIFY_CODE_SESSION = new ConcurrentHashMap<String, VerifyCode>();//time主属
    private static final Logger CONSOLE = LoggerFactory.getLogger(VerifyQuartz.class);
    private final static int INVALIDTIME = 3;//session key失效时间 1分钟
    private final static int START = 0;//设置执行开始时间
    private final static int INTERVAL = 10000;//设置间隔执行时间 单位/毫秒

    // key code  time
    public static void put(String key, VerifyCode value) {
        VERIFY_CODE_SESSION.put(key, value);
    }

    public static VerifyCode get(String key) {
        Object obj = VERIFY_CODE_SESSION.get(key);
        if(null!=obj){
            return (VerifyCode)obj;
        }else {
            return null;
        }

    }

    public static boolean  remove(String key) {
        if(VERIFY_CODE_SESSION.containsKey(key)){
            VERIFY_CODE_SESSION.remove(key);
            return true;
        }else{
            return  false;
        }
    }


    static {
        Timer timer = new Timer();//定时类
        CONSOLE.info("start:"+LocalDateTime.now());
        timer.schedule(new TimerTask() {//创建一个定时任务
            @Override
            public void run() {
                CONSOLE.info("running...");
                for(Map.Entry<String,VerifyCode> entry : VERIFY_CODE_SESSION.entrySet()){
                    int invalidTime = entry.getValue().getInvalidTime()!=null?entry.getValue().getInvalidTime():INVALIDTIME;
                    if(entry.getValue().getCreateTime().plusMinutes(invalidTime).isBefore(LocalDateTime.now())){
                        CONSOLE.info("remove token:"+entry.getKey());
                        VERIFY_CODE_SESSION.remove(entry.getKey());
                    }
                }
            }
        }, START, INTERVAL);//从0秒开始，每隔10秒执行一次
    }

    public static void main(String[] args) {
        VerifyCode code = new VerifyCode("1");
        code.setCreateTime(LocalDateTime.now().plusMinutes(1));
        VerifyCode code1 = new VerifyCode("2");
        code1.setCreateTime(LocalDateTime.now().plusMinutes(2));
        VerifyCode code2 = new VerifyCode("3");
        code2.setCreateTime(LocalDateTime.now().plusMinutes(3));
        VerifyCode code4 = new VerifyCode("4");
        code4.setCreateTime(LocalDateTime.now().plusMinutes(4));
        VerifyCode code5 = new VerifyCode("5");
        code5.setCreateTime(LocalDateTime.now().plusMinutes(5));

        VerifyQuartz.put("1", code);
        VerifyQuartz.put("2", code1);
        VerifyQuartz.put("3", code2);
        VerifyQuartz.put("4", code4);
        VerifyQuartz.put("5", code5);
        VerifyQuartz.put("6", code5);
    }

}

