package org.ndshop.goods.enums;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-29 11:02]
 * @Description: [商品是否是新品]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public enum GoodsNewFlagStatus {
    ISNEW(0),//新品
    isOld(1)//非新品
    ;

    private int code ;

    GoodsNewFlagStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
