package org.ndshop.goods.dto;

import org.ndshop.dbs.jpa.dto.BaseDto;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品品牌数据传输]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class GoodsBrandsDto extends BaseDto {
    String goodsBrandsCategoryId ;//商品品牌分类id

    public String getGoodsBrandsCategoryId() {
        return goodsBrandsCategoryId;
    }

    public void setGoodsBrandsCategoryId(String goodsBrandsCategoryId) {
        this.goodsBrandsCategoryId = goodsBrandsCategoryId;
    }
}
