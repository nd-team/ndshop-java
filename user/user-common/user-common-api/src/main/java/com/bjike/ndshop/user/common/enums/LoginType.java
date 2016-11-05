package com.bjike.ndshop.user.common.enums;

/**
 * Created by lgq on 16-11-5.
 */
public enum  LoginType {
    NONE(0),//未识别
    APP(1),//app
    PC(2),//pc
    ;

    private int code;
    LoginType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
