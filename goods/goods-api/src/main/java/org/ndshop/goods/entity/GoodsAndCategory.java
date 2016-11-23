package org.ndshop.goods.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by tanghaixiang on 16-11-23.
 * 商品与分类的中间表
 */
@Entity
@Table(name = "goodsAndCategory")
public class GoodsAndCategory  extends BaseEntity{

    @OneToOne(optional = false, cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name="goods_id")
    @JSONField(serialize = false)
    private Goods goods;

    @ManyToOne(cascade = {CascadeType.REFRESH} ,fetch = FetchType.LAZY)
    @JoinColumn(name = "goodsCategory_id")
    private GoodsCategory goodsCategory;

    @ManyToOne(cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "goodsSecondCategory_id")
    private GoodsSecondCategory goodsSecondCategory;

    @ManyToOne(cascade = {CascadeType.REFRESH} ,fetch = FetchType.LAZY)
    @JoinColumn(name = "goodsThirdCategory_id")
    private GoodsThirdCategory goodsThirdCategory;

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
