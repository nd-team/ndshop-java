package org.ndshop.goods.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.ndshop.user.common.entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-30 14:50]
 * @Description: [商品收藏]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "goodsCollection")
public class GoodsCollection extends BaseEntity{

    @OneToOne(cascade = {CascadeType.REFRESH} )
    @JoinColumn( name = "goods_id" ,nullable = false)
    private Goods goods;

    @OneToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime = LocalDateTime.now();//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime modifyTime = LocalDateTime.now();//修改时间

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
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

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }
}
