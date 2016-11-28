package org.ndshop.goods.entity;

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
    @Column(nullable = false ,unique = true)
    private String goodsNum; //商品编号

    @Column(nullable = false ,unique = true)
    private String name;//商品名

    private String goodsDescription; //商品描述

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

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
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
}

