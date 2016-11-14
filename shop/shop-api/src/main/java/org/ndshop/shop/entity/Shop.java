package org.ndshop.shop.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.ndshop.shop.enums.ShopStatus;

import org.ndshop.user.common.entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by ike on 16-11-10.
 */
@Entity
@Table(name="nd_shop")
public class Shop extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.REFRESH,targetEntity = User.class)
    @JoinColumn(name="user_id",nullable = false)
    private User owner;
    //店铺拥有者

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition="dateTime") //指定数据库类型
    private LocalDateTime createTime;
    //店铺创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition="dateTime") //指定数据库类型
    private LocalDateTime lastModiTime;
    //最后修改时间

    @Column(name="status",columnDefinition = "int")
    private ShopStatus status;
    //店铺状态

    @Column(columnDefinition = "varchar(600)",nullable = true)
    private String intro;
    //店铺介绍（介绍文字）

    @Column(columnDefinition = "varchar(100)",nullable = true)
    private String shortIntro;
    //店铺介绍摘要

    @Column(columnDefinition = "varchar(100)",nullable = true)
    private String shopImg;
    //店铺图片

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "shop")
    private Set<Category> categorySet;
    //店内分类

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastModiTime() {
        return lastModiTime;
    }

    public void setLastModiTime(LocalDateTime lastModiTime) {
        this.lastModiTime = lastModiTime;
    }

    public ShopStatus getStatus() {
        return status;
    }

    public void setStatus(ShopStatus status) {
        this.status = status;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getShortIntro() {
        return shortIntro;
    }

    public void setShortIntro(String shortIntro) {
        this.shortIntro = shortIntro;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public Set<Category> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<Category> categorySet) {
        this.categorySet = categorySet;
    }
}
