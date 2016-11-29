package org.ndshop.goods.enums;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-29 11:15]
 * @Description: [商品是否推荐]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public enum GoodsRecommendStatus {

    isRecommend(0),//推荐
    isnormal(1)//正常卖
    ;

    private int code;

    GoodsRecommendStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
