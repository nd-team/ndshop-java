package org.ndshop.goods.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by ike on 16-11-5.
 */
@Entity
@Table(name = "nd_goods_inventory")
public class GoodsInventory extends BaseEntity{
    //店铺ｉｄ＇商品ｉｄ＇数量＇出售数量＇修改时间＇创建时间

    @Column(nullable = false)
    private Long quanty;//现存数量
    private Long hasSaleQuanty;//已卖出数量

    @OneToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name="goods_id")
    @JSONField(serialize = false)
    private Goods goods;

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
