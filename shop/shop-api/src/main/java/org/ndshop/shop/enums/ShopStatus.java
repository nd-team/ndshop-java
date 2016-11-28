package org.ndshop.shop.enums;

/**
 * @Author: [caixianyong]
 * @Date: [2016-11-23 16:51]
 * @Description: [店铺模块状态码]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public enum ShopStatus {
    ONLINE(0),
    OFFLINE(1);

    private int code;//状态码

    ShopStatus(int code){this.code = code;}

    public int getCode(){return this.code;}
}
