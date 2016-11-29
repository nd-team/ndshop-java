package org.ndshop.goods.enums;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-29 10:57]
 * @Description: [商品是否特价卖]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public enum GoodsSpecialSaleStatus {
    SPECIALSALE(0),//特价卖
    NORMALSALE(1)//非特价卖
    ;

    private int code;

    GoodsSpecialSaleStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
