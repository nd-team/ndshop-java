package org.ndshop.goods.enums;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-29 10:50]
 * @Description: [商品状态]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public enum GoodsOnSaleStatus {

    ONSALE(0),//上架
    OFFSALE(1)//下架
    ;

    private int code;
    GoodsOnSaleStatus( int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
