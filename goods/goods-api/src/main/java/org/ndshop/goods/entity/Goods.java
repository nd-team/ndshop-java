package org.ndshop.goods.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    @Column(nullable = false)
    private String goodsName;//名
    @Column(nullable = true, precision = 12, scale = 2)//12位数字可保留两位小数，可以为空
    private Double price;//价格
    private Double goodsLength;//长
    private Double goodsWidth;//宽
    private Double goodsHeight;//高
    private Double goodsWeight;//重量
    private String goodsColor;//颜色

    @JSONField(serialize = false)
    @OneToOne(optional = true, mappedBy = "goods", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private GoodsAndCategory goodsAndCategory;//商品和分类

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "goodsBrand_id")
    private GoodsBrand goodsBrand;//商品品牌

    @JSONField(serialize = false)
    @OneToOne(optional = false, mappedBy = "goods", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private GoodsDes goodsDes;//商品描述

    @JSONField(serialize = false)
    @OneToOne(optional = false, mappedBy = "goods", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private GoodsInventory goodsInventory;//库存数量

//    @OneToMany(mappedBy = "goods", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
//    private Set<GoodsShops> goodsShops;

    @JSONField(serialize = false)
    @OneToMany(mappedBy = "goods", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<GoodsPic> goodsPic;//商品图片

    @JSONField(serialize = false)
    @OneToMany(mappedBy = "goods", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<GoodsCollection> goodsCollections;//商品收藏

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition = "dateTime") //指定数据库类型
    private LocalDateTime createTime = LocalDateTime.now();//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition = "dateTime") //指定数据库类型
    private LocalDateTime modifyTime = LocalDateTime.now();//修改时间

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getGoodsLength() {
        return goodsLength;
    }

    public void setGoodsLength(Double goodsLength) {
        this.goodsLength = goodsLength;
    }

    public Double getGoodsWidth() {
        return goodsWidth;
    }

    public void setGoodsWidth(Double goodsWidth) {
        this.goodsWidth = goodsWidth;
    }

    public Double getGoodsHeight() {
        return goodsHeight;
    }

    public void setGoodsHeight(Double goodsHeight) {
        this.goodsHeight = goodsHeight;
    }

    public Double getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(Double goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getGoodsColor() {
        return goodsColor;
    }

    public void setGoodsColor(String goodsColor) {
        this.goodsColor = goodsColor;
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

    public GoodsAndCategory getGoodsAndCategory() {
        return goodsAndCategory;
    }

    public void setGoodsAndCategory(GoodsAndCategory goodsAndCategory) {
        this.goodsAndCategory = goodsAndCategory;
    }

    public GoodsBrand getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(GoodsBrand goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public GoodsDes getGoodsDes() {
        return goodsDes;
    }

    public void setGoodsDes(GoodsDes goodsDes) {
        this.goodsDes = goodsDes;
    }

    public GoodsInventory getGoodsInventory() {
        return goodsInventory;
    }

    public void setGoodsInventory(GoodsInventory goodsInventory) {
        this.goodsInventory = goodsInventory;
    }

    //    public Set<GoodsShops> getGoodsShops() {
//        return goodsShops;
//    }
//
//    public void setGoodsShops(Set<GoodsShops> goodsShops) {
//        this.goodsShops = goodsShops;
//    }
//
    public Set<GoodsPic> getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(Set<GoodsPic> goodsPic) {
        this.goodsPic = goodsPic;
    }

    public Set<GoodsCollection> getGoodsCollections() {
        return goodsCollections;
    }

    public void setGoodsCollections(Set<GoodsCollection> goodsCollections) {
        this.goodsCollections = goodsCollections;
    }
}

