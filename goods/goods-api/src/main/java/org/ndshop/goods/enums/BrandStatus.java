package org.ndshop.goods.enums;

/**
 * Created by ike on 16-11-14.
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
