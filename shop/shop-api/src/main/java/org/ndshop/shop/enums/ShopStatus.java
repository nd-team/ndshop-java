package org.ndshop.shop.enums;

/**
 * Created by ike on 16-11-10.
 */
public enum ShopStatus {
    ONLINE(0),
    OFFLINE(1);

    private int code;//状态码

    ShopStatus(int code){this.code = code;}

    public int getCode(){return this.code;}
}
