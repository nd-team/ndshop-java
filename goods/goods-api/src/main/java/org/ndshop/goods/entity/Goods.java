package org.ndshop.goods.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.ndshop.goods.enums.*;
import org.ndshop.user.common.entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-23 17:31]
 * @Description: [商品]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "goods")
public class Goods extends BaseEntity {
    @Column(nullable = false ,unique = true)
    private String goodsNum; //商品编号

    private String goodsCode;//商品条形码

    @Column(nullable = false ,unique = true)
    private String name;//商品名

    private String goodsDescription; //商品描述

    @Min(value = 0)
    @Column(scale = 2)
    private Double price ;//销售价

    @Min(value = 0)
    @Column(scale = 2)
    private Double discountPrice ;//折扣价

    @Min(value = 0)
    private Integer quantity ;//总库存

    @Column(columnDefinition ="INT default 0" )
    private GoodsOnSaleStatus goodsOnSaleStatus;//商品上架状态 默认上架

    @Column(columnDefinition = "INT default 1")
    private GoodsSpecialSaleStatus goodsSpecialSaleStatus;//商品是否特卖 默认正常卖

    @Column(columnDefinition = "INT default 1")
    private GoodsNewFlagStatus goodsNewFlagStatus;//商品是否是新品 默认正常卖

    @Column(columnDefinition = "INT default 1")
    private GoodsHotSaleStatus goodsHotSaleStatus;//商品是否是热卖 默认正常卖

    @Column(columnDefinition = "INT default 1")
    private GoodsRecommendStatus goodsRecommendStatus;//商品是否推荐 默认正常卖

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(columnDefinition="dateTime")
    private LocalDateTime createTime = LocalDateTime.now();//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(columnDefinition="dateTime")
    private LocalDateTime modifyTime = LocalDateTime.now();//修改时间


    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "goods" , fetch = FetchType.LAZY)
    private Set<GoodsPic> goodsPic ;//商品图片

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.REMOVE} )
    @JoinColumn(name = "goodsCategory_id")
    private GoodsCategory goodsCategory; //商品种类

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinColumn(name = "goodsBrands_id")
    private GoodsBrands goodsBrands; //商品品牌表

    @OneToOne(cascade = CascadeType.ALL ,mappedBy = "goods", fetch = FetchType.LAZY)
    private GoodsFieldsValue goodsFieldValue;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinColumn(name = "user_id")
    private User user; //商品品牌表

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public GoodsNewFlagStatus getGoodsNewFlagStatus() {
        return goodsNewFlagStatus;
    }

    public void setGoodsNewFlagStatus(GoodsNewFlagStatus goodsNewFlagStatus) {
        this.goodsNewFlagStatus = goodsNewFlagStatus;
    }

    public GoodsHotSaleStatus getGoodsHotSaleStatus() {
        return goodsHotSaleStatus;
    }

    public void setGoodsHotSaleStatus(GoodsHotSaleStatus goodsHotSaleStatus) {
        this.goodsHotSaleStatus = goodsHotSaleStatus;
    }

    public GoodsRecommendStatus getGoodsRecommendStatus() {
        return goodsRecommendStatus;
    }

    public void setGoodsRecommendStatus(GoodsRecommendStatus goodsRecommendStatus) {
        this.goodsRecommendStatus = goodsRecommendStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Set<GoodsPic> getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(Set<GoodsPic> goodsPic) {
        this.goodsPic = goodsPic;
    }

    public GoodsCategory getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(GoodsCategory goodsCategory) {
        this.goodsCategory = goodsCategory;
    }

    public GoodsBrands getGoodsBrands() {
        return goodsBrands;
    }

    public void setGoodsBrands(GoodsBrands goodsBrands) {
        this.goodsBrands = goodsBrands;
    }

    public GoodsFieldsValue getGoodsFieldValue() {
        return goodsFieldValue;
    }

    public void setGoodsFieldValue(GoodsFieldsValue goodsFieldValue) {
        this.goodsFieldValue = goodsFieldValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

