package com.bjike.ndshop.user.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.bjike.ndshop.dbs.jpa.entity.BaseEntity;
import com.bjike.ndshop.user.common.enums.LoginType;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by lgq on 16-11-5.
 */
@Entity
@Table(name = "nd_user_login_log")
public class UserLoginLog extends BaseEntity {
//    登录时间	登录地点	ip地址	登录方式
    private LocalDateTime loginTime;
    private String loginAddress;
    private String loginIp;
    private LoginType loginType;


    @ManyToOne(cascade = {CascadeType.ALL},optional = false)
    @JoinColumn(name = "user_id", nullable = false)
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
