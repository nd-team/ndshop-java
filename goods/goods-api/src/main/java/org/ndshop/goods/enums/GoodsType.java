package org.ndshop.goods.enums;

/**
 * Created by ike on 16-11-5.
 */
public enum GoodsCategory {
    ELECTRC(0)//家用电器
    , CLOTHES(1)//服饰鞋包
    , BEAUTY(2)//个护美妆
    , Digital(3)//手机数码
    , UNREVIEW(4)//百货食品
    , SPORT(5)//运动健身
    ;

    private int code;

    GoodsCategory(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
