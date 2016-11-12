package org.ndshop.shop.entity;



import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.ndshop.goods.entity.Goods;
import org.ndshop.shop.enums.ShopStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by ike on 16-11-10.
 */
@Entity
@Table(name = "nd_shop_categroy")
public class Categroy extends BaseEntity {

    @Column(columnDefinition = "varchar(30)", nullable = false)
    private String name;
    //店内分类名称
/*
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    private Shop shop;*/

    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "goods")
    private Set<Goods> goodsSet;*/

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(columnDefinition = "dateTime")
    private LocalDateTime createTime;
    //分类创建时间

    @Column(name = "status",columnDefinition = "int(1)")
    private ShopStatus status;
    //分类架状态码


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }*/

//    public Set<Goods> getGoodsSet() {
//        return goodsSet;
//    }
//
//    public void setGoodsSet(Set<Goods> goodsSet) {
//        this.goodsSet = goodsSet;
//    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public ShopStatus getStatus() {
        return status;
    }

    public void setStatus(ShopStatus status) {
        this.status = status;
    }
}
