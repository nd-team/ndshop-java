package org.ndshop.goods.enums;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品品牌申请状态]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public enum BrandStatus {
    ACTIVE("ACTIVE")   //激活
    ,FROZEN("FROZEN") //冻结
    ;
    private String name;

    BrandStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
