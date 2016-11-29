package org.ndshop.shop.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.ndshop.shop.enums.ShopStatus;
import org.ndshop.user.common.entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @Author: [caixianyong]
 * @Date: [2016-11-23 16:51]
 * @Description: [店铺]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity(name = "店铺")
@Table(name="shop")
public class Shop extends BaseEntity {

    @Column(nullable = false,unique = true)
    private String name;

    @ManyToOne(cascade = CascadeType.REFRESH,targetEntity = User.class,optional = false)
    @JoinColumn(name="user_id",nullable = true)
    private User user;
    //店铺拥有者

    @Column(name = "locate",nullable = true,columnDefinition = "varchar(20)")
    private String locate;
    //店铺定位

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

    @OneToMany(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    private Set<Logistics> logisticses;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "shop")
    private Set<Category> categorySet;
    //店内分类

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public Set<Logistics> getLogisticses() {
        return logisticses;
    }

    public void setLogisticses(Set<Logistics> logisticses) {
        this.logisticses = logisticses;
    }

    public Set<Category> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<Category> categorySet) {
        this.categorySet = categorySet;
    }
}
