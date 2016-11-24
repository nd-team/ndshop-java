package org.ndshop.goods.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-23 17:39]
 * @Description: [三级分类]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "goodsThirdCategory")
public class GoodsThirdCategory extends BaseEntity {

    @Column(nullable = false ,unique = true)
    private  String thirdName ;//三级分类名
    @Column
    private String pinyin;//拼音

    @ManyToOne(cascade = {CascadeType.REFRESH} ,fetch = FetchType.LAZY)
    @JoinColumn(name = "goodsSecondCategory_id")
    private GoodsSecondCategory goodsSecondCategory;//二级分类

    @MapKeyJoinColumn
    @OneToMany(mappedBy = "goodsThirdCategory", cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Set<GoodsAndCategory> goodsAndCategory;//商品和分类

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition="dateTime") //指定数据库类型
    private LocalDateTime createTime = LocalDateTime.now();//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition="dateTime") //指定数据库类型
    private LocalDateTime modifyTime = LocalDateTime.now();//修改时间

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public GoodsSecondCategory getGoodsSecondCategory() {
        return goodsSecondCategory;
    }

    public void setGoodsSecondCategory(GoodsSecondCategory goodsSecondCategory) {
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

