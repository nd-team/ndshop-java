package org.ndshop.goods.enums;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品二级类型]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public enum GoodsType {
    ELECTRC(0)//家用电器
    , CLOTHES(1)//服饰鞋包
    , BEAUTY(2)//个护美妆
    , Digital(3)//手机数码
    , UNREVIEW(4)//百货食品
    , SPORT(5)//运动健身
    ;

    private int code;

    GoodsType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
