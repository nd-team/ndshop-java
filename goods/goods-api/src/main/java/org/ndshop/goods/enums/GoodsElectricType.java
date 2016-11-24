package org.ndshop.goods.enums;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品类型]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public enum GoodsElectricType {
    BIGELECTRIC(0)//大家电
    , AUDIOELECTRIC(1) //2.影音电器
    , LIVINGELECTRIC(2) //3.生活电器
    , KITCHENELECTRIC(3)//4.厨房电器
    , HEALTHELECTRIC(4)//5.家庭保健
    ;
    private int code;

    GoodsElectricType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
