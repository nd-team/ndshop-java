package org.ndshop.goods.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.bjike.ndshop.dbs.jpa.entity.BaseEntity;
import com.bjike.ndshop.user.common.entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by ike on 16-11-5.
 */
@Entity
@Table(name = "nd_shops")
public class Shops extends BaseEntity {
    //店铺名称＇商家用户＇店铺地址＇
    @Column(nullable = true)
    private String shopsName;

    @OneToMany(mappedBy = "shops", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<GoodsShops> goodsShops ;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    @JSONField(serialize = false)
    private User user;

    private String addr;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition="dateTime") //指定数据库类型
    private LocalDateTime createTime = LocalDateTime.now();//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition="dateTime") //指定数据库类型
    private LocalDateTime modifyTime = LocalDateTime.now();//修改时间

    public String getShopsName() {
        return shopsName;
    }

    public void setShopsName(String shopsName) {
        this.shopsName = shopsName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
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

    public Set<GoodsShops> getGoodsShops() {
        return goodsShops;
    }

    public void setGoodsShops(Set<GoodsShops> goodsShops) {
        this.goodsShops = goodsShops;
    }
}
