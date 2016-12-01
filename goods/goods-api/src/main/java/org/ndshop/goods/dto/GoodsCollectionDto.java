package org.ndshop.goods.dto;

import org.ndshop.dbs.jpa.dto.BaseDto;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-30 14:47]
 * @Description: [商品收藏数据传输]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class GoodsCollectionDto extends BaseDto {

    private String userId ;//用户id
    private String goodsId ;//商品id

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
