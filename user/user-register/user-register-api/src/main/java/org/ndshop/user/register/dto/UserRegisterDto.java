package org.ndshop.user.register.dto;


import org.ndshop.dbs.jpa.dto.BaseDto;

/**
 * Created by lgq on 16-11-2.
 */
public class UserRegisterDto extends BaseDto {

    private String username; //注册用名
    private String password;//注册密码
    private String rePassword;//重复密码
    private String phone; //注册手机
    private String phone_code ;//手机验证码
    private String captcha;//验证码

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone_code() {
        return phone_code;
    }

    public void setPhone_code(String phone_code) {
        this.phone_code = phone_code;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
