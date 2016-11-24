package org.ndshop.goods.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;

import javax.persistence.*;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-23 17:39]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "goodsShops")
public class GoodsShops extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "goods_id")
    private Goods goods;//商品

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "shops_id")
    private Shops shops;//店铺

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Shops getShops() {
        return shops;
    }

    public void setShops(Shops shops) {
        this.shops = shops;
    }
}

