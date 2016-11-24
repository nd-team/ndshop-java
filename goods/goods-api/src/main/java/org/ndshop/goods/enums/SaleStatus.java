package org.ndshop.goods.enums;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品上下架状态]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
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
