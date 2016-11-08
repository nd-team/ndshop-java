package org.ndshop.goods.entity;

import com.bjike.ndshop.dbs.jpa.entity.BaseEntity;

import javax.persistence.*;


/**
 * Created by ike on 16-11-7.
 */
@Entity
@Table(name = "nd_goods_shops")
public class GoodsShops extends BaseEntity{

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "shops_id")
    private Shops shops;

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
