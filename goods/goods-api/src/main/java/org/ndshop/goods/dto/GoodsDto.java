package org.ndshop.goods.dto;

import org.ndshop.dbs.jpa.dto.BaseDto;
import org.ndshop.goods.enums.*;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-28 10:30]
 * @Description: [商品数据传输]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class GoodsDto extends BaseDto{
    private String brandId ;//商品品牌id
    private String categoryId;//商品种类id
    private String userId;//用户id

    private GoodsOnSaleStatus goodsOnSaleStatus;//是否上架状态
    private GoodsSpecialSaleStatus goodsSpecialSaleStatus;//是否是特卖
    private GoodsHotSaleStatus goodsHotSaleStatus;//是否热卖状态
    private GoodsNewFlagStatus goodsNewFlagStatus;//是否是新品状态
    private GoodsRecommendStatus goodsRecommendStatus; //商品推荐状态

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

    public GoodsOnSaleStatus getGoodsOnSaleStatus() {
        return goodsOnSaleStatus;
    }

    public void setGoodsOnSaleStatus(GoodsOnSaleStatus goodsOnSaleStatus) {
        this.goodsOnSaleStatus = goodsOnSaleStatus;
    }

    public GoodsSpecialSaleStatus getGoodsSpecialSaleStatus() {
        return goodsSpecialSaleStatus;
    }

    public void setGoodsSpecialSaleStatus(GoodsSpecialSaleStatus goodsSpecialSaleStatus) {
        this.goodsSpecialSaleStatus = goodsSpecialSaleStatus;
    }

    public GoodsHotSaleStatus getGoodsHotSaleStatus() {
        return goodsHotSaleStatus;
    }

    public void setGoodsHotSaleStatus(GoodsHotSaleStatus goodsHotSaleStatus) {
        this.goodsHotSaleStatus = goodsHotSaleStatus;
    }

    public GoodsNewFlagStatus getGoodsNewFlagStatus() {
        return goodsNewFlagStatus;
    }

    public void setGoodsNewFlagStatus(GoodsNewFlagStatus goodsNewFlagStatus) {
        this.goodsNewFlagStatus = goodsNewFlagStatus;
    }

    public GoodsRecommendStatus getGoodsRecommendStatus() {
        return goodsRecommendStatus;
    }

    public void setGoodsRecommendStatus(GoodsRecommendStatus goodsRecommendStatus) {
        this.goodsRecommendStatus = goodsRecommendStatus;
    }
}
