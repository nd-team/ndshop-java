package org.ndshop.goods.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-23 17:37]
 * @Description: [商品与分类的中间表]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "goodsAndCategory")
public class GoodsAndCategory  extends BaseEntity {

    @OneToOne(optional = false, cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name="goods_id")
    @JSONField(serialize = false)
    private Goods goods;//商品

    @ManyToOne(cascade = {CascadeType.REFRESH} ,fetch = FetchType.LAZY)
    @JoinColumn(name = "goodsCategory_id")
    private GoodsCategory goodsCategory;//商品一级分类

    @ManyToOne(cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "goodsSecondCategory_id")
    private GoodsSecondCategory goodsSecondCategory;//商品二级分类

    @ManyToOne(cascade = {CascadeType.REFRESH} ,fetch = FetchType.LAZY)
    @JoinColumn(name = "goodsThirdCategory_id")
    private GoodsThirdCategory goodsThirdCategory;//商品三级分类

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition="dateTime" ,nullable = false) //指定数据库类型
    private LocalDateTime createTime = LocalDateTime.now();//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition="dateTime" ,nullable = false) //指定数据库类型
    private LocalDateTime modifyTime = LocalDateTime.now();//修改时间

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public GoodsCategory getGoodsCategory() {
        return goodsCategory;
    }

    public void setGoodsCategory(GoodsCategory goodsCategory) {
        this.goodsCategory = goodsCategory;
    }

    public GoodsSecondCategory getGoodsSecondCategory() {
        return goodsSecondCategory;
    }

    public void setGoodsSecondCategory(GoodsSecondCategory goodsSecondCategory) {
        this.goodsSecondCategory = goodsSecondCategory;
    }

    public GoodsThirdCategory getGoodsThirdCategory() {
        return goodsThirdCategory;
    }

    public void setGoodsThirdCategory(GoodsThirdCategory goodsThirdCategory) {
        this.goodsThirdCategory = goodsThirdCategory;
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

