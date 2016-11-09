package org.ndshop.shop.entity;

import com.dounine.corgi.entity.BaseEntity;
import org.ndshop.goods.entity.Goods;
import org.ndshop.shop.enums.ShopStaus;
import org.ndshop.user.common.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Created by ike on 16-11-9.
 */
@Entity
@Table(name = "nd_shop")
public class Shop extends BaseEntity {

    @Column(nullable = false)
    private String storeName;

    @ManyToOne(cascade = {CascadeType.ALL}, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shop")
    private List<Goods> goodsList;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime accessTime; //店铺创建时间

    @Column(nullable = false)
    private ShopStaus status;   //店铺状态

    @Column(nullable = false)
    private String details;     //店铺详情

    @Column(nullable = false)
    private String images;      //店铺图片

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }

    public ShopStaus getStatus() {
        return status;
    }

    public void setStatus(ShopStaus status) {
        this.status = status;
    }
}
