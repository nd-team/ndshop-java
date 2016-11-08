package org.ndshop.goods.enums;

/**
 * Created by ike on 16-11-5.
 */
public enum SaleStatus {
    ONSHELF(0)   //上架
    ,OFFSHELF(1) //下架
    ;
    private int code;

    SaleStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
