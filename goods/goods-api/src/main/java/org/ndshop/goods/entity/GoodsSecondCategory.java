package org.ndshop.goods.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by tanghaixiang on 16-11-21.
 * 商品二级分类 如：精品女装
 */
@Entity
@Table(name = "goodsSecondCategory")
public class GoodsSecondCategory extends BaseEntity{

    @Column(nullable = false ,unique = true)
    private  String secondName ;

    @Column
    private String pinyin;//拼音

    @ManyToOne(cascade = {CascadeType.REFRESH} ,fetch = FetchType.LAZY)
    @JoinColumn(name = "goodsCategory_id")
    private GoodsCategory goodsCategory;

    @OneToMany(mappedBy = "goodsSecondCategory", cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Set<GoodsThirdCategory> goodsThirdCategory;

    @OneToMany(mappedBy = "goodsSecondCategory", cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Set<GoodsAndCategory> goodsAndCategory;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition="dateTime") //指定数据库类型
    private LocalDateTime createTime = LocalDateTime.now();//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition="dateTime") //指定数据库类型
    private LocalDateTime modifyTime = LocalDateTime.now();//修改时间

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public GoodsCategory getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(GoodsCategory goodsCategory) {
        this.goodsCategory = goodsCategory;
    }

    public Set<GoodsThirdCategory> getGoodsThirdCategory() {
        return goodsThirdCategory;
    }

    public void setGoodsThirdCategory(Set<GoodsThirdCategory> goodsThirdCategory) {
        this.goodsThirdCategory = goodsThirdCategory;
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
