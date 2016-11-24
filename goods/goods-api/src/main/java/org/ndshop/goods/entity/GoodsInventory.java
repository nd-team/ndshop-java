package org.ndshop.goods.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-23 17:34]
 * @Description: [商品数量，库存]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "goodsInventory")
public class GoodsInventory extends BaseEntity {
    @Column(nullable = false)
    private Long quanty;//现存数量
    private Long hasSaleQuanty;//已卖出数量

    @OneToOne(optional = false, cascade = CascadeType.REFRESH ,fetch = FetchType.LAZY)
    @JoinColumn(name="goods_id")
    @JSONField(serialize = false)
    private Goods goods;//商品

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

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}

