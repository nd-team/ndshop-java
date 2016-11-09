package org.ndshop.shop.enums;

/**
 * Created by ike on 16-11-9.
 */
public enum ShopStaus {
    ONLINE(0),  //店铺上线
    OFFLINE(1); //店铺下线

    private int code;

    public int getCode(){return this.code;}

    ShopStaus(int code){
        this.code = code;
    }
}
