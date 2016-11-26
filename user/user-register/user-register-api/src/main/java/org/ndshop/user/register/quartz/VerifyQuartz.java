package org.ndshop.user.register.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: 验证码定时失效辅助工具]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class VerifyQuartz {
    private static final Logger CONSOLE = LoggerFactory.getLogger(VerifyQuartz.class);
    private final static int INVALID_TIME = 3;//session key失效时间 1分钟
    private final static int START = 0;//设置执行开始时间
    private final static int INTERVAL = 5000;//设置间隔执行时间 单位/毫秒

    private Map<String, VerifyCode> verifySession;

    public VerifyQuartz(Map<String, VerifyCode> verifySession) {
        this.verifySession = verifySession;
        startTimer();
    }
    private void startTimer(){
        Timer timer = new Timer();//定时类
        timer.schedule(new TimerTask() {//创建一个定时任务
            @Override
            public void run() {
                for (Map.Entry<String, VerifyCode> entry : verifySession.entrySet()) {
                    int invalidTime = entry.getValue().getInvalidTime() != null ? entry.getValue().getInvalidTime() : INVALID_TIME;
                    if (entry.getValue().getCreateTime().plusMinutes(invalidTime).isBefore(LocalDateTime.now())) {
                        CONSOLE.info("remove token:" + entry.getKey());
                        verifySession.remove(entry.getKey());
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

        VerifySession.put("1", code);
        VerifySession.put("2", code1);
        VerifySession.put("3", code2);
        VerifySession.put("4", code4);
        VerifySession.put("5", code5);
        VerifySession.put("6", code5);
    }

}

