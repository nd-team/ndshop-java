package org.ndshop.goods.dto;

import org.ndshop.dbs.jpa.dto.BaseDto;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-28 10:30]
 * @Description: [商品数据传输]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class GoodsDto extends BaseDto{
    String brandId ;//商品品牌id
    String categoryId;//商品种类id
    String userId;//用户id

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
