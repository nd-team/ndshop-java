package org.ndshop.goods.enums;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品收藏状态]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public enum CollectionStatus {
    FOUCING(0)   //已关注
    ,CONCEL(1) //取消关注
    ;
    private int code;

    CollectionStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
