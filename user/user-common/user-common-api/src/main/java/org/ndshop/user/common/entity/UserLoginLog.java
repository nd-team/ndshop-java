package org.ndshop.user.common.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.ndshop.user.common.enums.LoginType;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by lgq on 16-11-5.
 * 用户登陆日志
 */
@Entity
@Table(name = "user_login_log")
public class UserLoginLog extends BaseEntity {

    private LocalDateTime loginTime;//    登录时间
    private String loginAddress;// 登录地点
    private String loginIp;// ip地址
    @Column(columnDefinition = "INT(1)")
    private LoginType loginType;// 登录方式


    @ManyToOne(cascade = {CascadeType.ALL}, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
