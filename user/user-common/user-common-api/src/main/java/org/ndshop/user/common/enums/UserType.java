package org.ndshop.user.common.enums;

/**
 * Created by lgq on 16-11-8.
 */
public enum  UserType {
    CUSTOMER(0),//无
    ADMIN(1),//男
    ;

    private int code;
    UserType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
