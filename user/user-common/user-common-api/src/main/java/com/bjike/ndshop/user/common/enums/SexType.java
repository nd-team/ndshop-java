package com.bjike.ndshop.user.common.enums;

/**
 * Created by lgq on 16-11-5.
 * 性别
 */
public enum  SexType {
    NONE(0),//无
    WOMAN(1),//男
    MAN(2),//女
    ;

    private int code;
    SexType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
