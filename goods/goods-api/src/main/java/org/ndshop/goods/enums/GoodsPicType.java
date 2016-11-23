package org.ndshop.goods.enums;

/**
 * Created by ike on 16-11-19.
 */
public enum GoodsPicType {
    /**
     * log图片用于展示一个品牌
     * displaypic图片用于商品展示
     * detailpic用于商品详情图片
     */
    LOGPIC(0), DISPALYPIC(1), DETAILPIC(2);

    private int code;

    GoodsPicType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
