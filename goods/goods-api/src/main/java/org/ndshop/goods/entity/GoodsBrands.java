package org.ndshop.goods.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.ndshop.goods.enums.GoodsBrandsRecommendStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-26 11:43]
 * @Description: [商品品牌]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "goodsBrands")
public class GoodsBrands extends BaseEntity {

    @Column(name = "goodsBrandsName" ,unique = true )
    private String name ;//商品品牌名

    @Column(unique = true )
    private String trademark ;//商标，log

    private GoodsBrandsRecommendStatus goodsBrandsRecommendStatus;//是否推荐

    @Column
    private String keyWord ; //关键字

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(columnDefinition="dateTime")
    private LocalDateTime createTime = LocalDateTime.now();//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(columnDefinition="dateTime")
    private LocalDateTime modifyTime = LocalDateTime.now();//修改时间

    @ManyToOne(cascade = {CascadeType.REFRESH })
    @JoinColumn(name = "goodsBrandsCategory_id")
    private GoodsBrandsCategory goodsBrandsCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public GoodsBrandsRecommendStatus getGoodsBrandsRecommendStatus() {
        return goodsBrandsRecommendStatus;
    }

    public void setGoodsBrandsRecommendStatus(GoodsBrandsRecommendStatus goodsBrandsRecommendStatus) {
        this.goodsBrandsRecommendStatus = goodsBrandsRecommendStatus;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
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

    public GoodsBrandsCategory getGoodsBrandsCategory() {
        return goodsBrandsCategory;
    }

    public void setGoodsBrandsCategory(GoodsBrandsCategory goodsBrandsCategory) {
        this.goodsBrandsCategory = goodsBrandsCategory;
    }
}
