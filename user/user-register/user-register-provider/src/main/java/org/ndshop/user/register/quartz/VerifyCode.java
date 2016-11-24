package org.ndshop.user.register.quartz;

import java.time.LocalDateTime;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: 验证码定时失效辅助工具实体]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class VerifyCode {
    private LocalDateTime createTime = LocalDateTime.now(); //创建时间
    private String code; //验证码
    private Integer invalidTime; //设置独立的失效时间(分钟)

    public VerifyCode(String code) {
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
