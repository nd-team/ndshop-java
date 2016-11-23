package org.ndshop.user.common.enums;

/**
 * Created by lgq on 16-11-8.
 * 用户类型
 */
public enum  UserType {
    CUSTOMER(0),//用户
    ADMIN(1),//管理员
    ;

    private int code;
    UserType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
