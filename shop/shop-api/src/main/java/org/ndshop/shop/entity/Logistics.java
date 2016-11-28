package org.ndshop.shop.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.ndshop.shop.enums.ShopStatus;

import javax.persistence.*;

/**
 * @Author: [caixianyong]
 * @Date: [2016-11-23 16:51]
 * @Description: [物流方案]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity(name = "物流方案")
@Table(name = "shop_logistics")
public class Logistics extends BaseEntity {

    @ManyToOne(targetEntity = Shop.class, cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_id")
    private Shop shop;
    //关联的店铺

    @Column(columnDefinition = "varchar(50)")
    private String name;
    //物流名称

    @Column(columnDefinition = "varchar(50)")
    private String expressComp;
    //快递公司

    @Column(columnDefinition = "double")
    private double basicWeight=0;
    //首重

    @Column(columnDefinition = "double")
    private double basicPrice=0;
    //首重价格

    @Column(columnDefinition = "double")
    private double extraWeigth=0;
    //续重

    @Column(columnDefinition = "double")
    private double extraPrice=0;
    //价格首重

    @Column(columnDefinition = "varchar(200)")
    private String area;
    //配送范围

    @Column(columnDefinition = "int")
    private ShopStatus status;

    @Column(columnDefinition = "int")
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

    public double getBasicWeight() {
        return basicWeight;
    }

    public void setBasicWeight(double basicWeight) {
        this.basicWeight = basicWeight;
    }

    public double getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(double basicPrice) {
        this.basicPrice = basicPrice;
    }

    public double getExtraWeigth() {
        return extraWeigth;
    }

    public void setExtraWeigth(double extraWeigth) {
        this.extraWeigth = extraWeigth;
    }

    public double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(double extraPrice) {
        this.extraPrice = extraPrice;
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
