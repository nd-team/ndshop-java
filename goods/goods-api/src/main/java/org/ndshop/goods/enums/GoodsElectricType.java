package org.ndshop.goods.enums;

/**
 * Created by ike on 16-11-5.
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
