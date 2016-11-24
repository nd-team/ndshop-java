package org.ndshop.goods.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-23 17:38]
 * @Description: [一级父级商品分类 如：服饰箱包]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "goodsCategory")
public class GoodsCategory extends BaseEntity {
    @Column(nullable = false ,unique = true)
    private String name ; //一级分类

    @OneToMany(mappedBy = "goodsCategory", cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Set<GoodsSecondCategory> goodsSecondCategory; //二级分类

    @OneToMany(mappedBy = "goodsCategory", cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Set<GoodsAndCategory> goodsAndCategory;//商品和分类

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition="dateTime") //指定数据库类型
    private LocalDateTime createTime = LocalDateTime.now();//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition="dateTime") //指定数据库类型
    private LocalDateTime modifyTime = LocalDateTime.now();//修改时间

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<GoodsSecondCategory> getGoodsSecondCategory() {
        return goodsSecondCategory;
    }

    public void setGoodsSecondCategory(Set<GoodsSecondCategory> goodsSecondCategory) {
        this.goodsSecondCategory = goodsSecondCategory;
    }

    public Set<GoodsAndCategory> getGoodsAndCategory() {
        return goodsAndCategory;
    }

    public void setGoodsAndCategory(Set<GoodsAndCategory> goodsAndCategory) {
        this.goodsAndCategory = goodsAndCategory;
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

