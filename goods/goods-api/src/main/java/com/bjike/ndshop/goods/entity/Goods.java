package com.bjike.ndshop.goods.entity;

import com.bjike.ndshop.dbs.jpa.entity.BaseEntity;
import com.bjike.ndshop.goods.enums.GoodsCategory;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by ike on 16-11-4.
 */
@Entity
@Table(name = "nd_goods")
public class Goods extends BaseEntity{
    @Column(nullable = true)
    private String goodsName ;//名
    @Column(name = "money", nullable = true, precision = 12, scale = 2)//12位数字可保留两位小数，可以为空
    private Double price ;//价格
    private Double goodsLength;//长
    private Double goodsWidth;//宽
    private Double goodsHeight;//高
    private Double goodsWeight;//重量
    private String goodsColor;//颜色

    private String firstCategory; //一级分类
    private String secondCategory; //二级分类
    
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition="dateTime") //指定数据库类型
    private LocalDateTime createTime = LocalDateTime.now();//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition="dateTime") //指定数据库类型
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

    public String getFirstCategory() {
        return firstCategory;
    }

    public void setFirstCategory(String firstCategory) {
        this.firstCategory = firstCategory;
    }

    public String getSecondCategory() {
        return secondCategory;
    }

    public void setSecondCategory(String secondCategory) {
        this.secondCategory = secondCategory;
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
}
