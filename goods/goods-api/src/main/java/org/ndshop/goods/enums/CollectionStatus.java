package org.ndshop.goods.enums;

/**
 * Created by ike on 16-11-15.
 */
public enum CollectionStatus {
    FOUCING(0)   //已关注
    ,NONFOUCING(1) //取消关注
    ;
    private int code;

    CollectionStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
