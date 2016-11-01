package com.bjike.ndshop.user.common.api.enums;

/**
 * Created by lgq on 16-10-26.
 * 会员类型
 */
public enum MemberType {
    REGISTERED(0),//注册会员
    BRONZE(1),//铜牌会员
    GOLD(2),//金牌会员
    ;

    private int code;
    MemberType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
