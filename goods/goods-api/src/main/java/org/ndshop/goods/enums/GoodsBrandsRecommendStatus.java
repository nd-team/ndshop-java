package org.ndshop.goods.enums;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-30 11:59]
 * @Description: [商品品牌推荐状态]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public enum  GoodsBrandsRecommendStatus {
    ISRECOMMEND(0),//品牌推荐
    NOTRECOMMEND(1)//品牌不推荐
    ;

    private int code ;

    GoodsBrandsRecommendStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
