package org.ndshop.goods.enums;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-29 11:11]
 * @Description: [商品是否热卖]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public enum GoodsHotSaleStatus {

    HOTSALE(0),//热卖
    NORMALSALE(1)//正常卖
    ;
    private int code;

    GoodsHotSaleStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
