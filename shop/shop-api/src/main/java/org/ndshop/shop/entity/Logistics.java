package org.ndshop.shop.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.ndshop.shop.enums.ShopStatus;

import javax.persistence.*;

/**
 * Created by ike on 16-11-21.
 */
@Entity(name = "物流")
@Table(name = "shop_logistics")
public class Logistics extends BaseEntity{

    @ManyToOne(targetEntity = Shop.class,cascade = CascadeType.DETACH)
    @JoinColumn(name = "shop_id")
    private Shop shop;
    //关联的店铺

    @Column(nullable = false,unique = true,columnDefinition = "varchar(50)")
    private String name;
    //物流名称

    @Column(nullable = false,columnDefinition = "varchar(50)")
    private String expressComp;
    //快递公司

    @Column(nullable = false,columnDefinition = "double")
    private double price;
    //价格

    @Column(nullable = false,columnDefinition = "double")
    private String area;
    //配送范围

    @Column(nullable = false,columnDefinition = "int")
    private ShopStatus status;

    @Column(nullable = false,columnDefinition = "int")
    private int seq;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpressComp() {
        return expressComp;
    }

    public void setExpressComp(String expressComp) {
        this.expressComp = expressComp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public ShopStatus getStatus() {
        return status;
    }

    public void setStatus(ShopStatus status) {
        this.status = status;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}
