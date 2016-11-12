package org.ndshop.user.register.quartz;

import java.time.LocalDateTime;

/**
 * Created by lgq on 16-11-8.
 * 验证码失效辅助实体
 */
public class VerifyCode {
    private LocalDateTime createTime = LocalDateTime.now(); //创建时间
    private String code; //验证码
    private Integer  invalidTime; //设置独立的失效时间(分钟)

    public VerifyCode(String code){
        this.code = code;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Integer invalidTime) {
        this.invalidTime = invalidTime;
    }
}
