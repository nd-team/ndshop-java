package com.bjike.ndshop.goods.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by ike on 16-11-5.
 */
public class GoodsInventory {
    //店铺ｉｄ＇商品ｉｄ＇数量＇出售数量＇修改时间＇创建时间

    private Long quanty;//现存数量
    private Long hasSaleQuanty;//已卖出数量

    @OneToOne(optional = true, cascade = CascadeType.ALL, mappedBy = "Goods")
    private Goods goodsId;

    @ManyToOne(cascade = {CascadeType.ALL},optional = false)
    @JoinColumn(name = "shops_id", nullable = true)
    private Shops shopsId;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition="dateTime") //指定数据库类型
    private LocalDateTime createTime = LocalDateTime.now();//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition="dateTime") //指定数据库类型
    private LocalDateTime modifyTime = LocalDateTime.now();//修改时间

    public Long getQuanty() {
        return quanty;
    }

    public void setQuanty(Long quanty) {
        this.quanty = quanty;
    }

    public Long getHasSaleQuanty() {
        return hasSaleQuanty;
    }

    public void setHasSaleQuanty(Long hasSaleQuanty) {
        this.hasSaleQuanty = hasSaleQuanty;
    }

    public Goods getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Goods goodsId) {
        this.goodsId = goodsId;
    }

    public Shops getShopsId() {
        return shopsId;
    }

    public void setShopsId(Shops shopsId) {
        this.shopsId = shopsId;
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
