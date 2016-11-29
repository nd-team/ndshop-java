package org.ndshop.shop.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.ndshop.shop.enums.ShopStatus;

import javax.persistence.*;
import java.util.Set;

/**
 * @Author: [caixianyong]
 * @Date: [2016-11-28 15:41]
 * @Description: [物流公司实体]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "shop_logisticsCompany")
public class LogisticsCompany extends BaseEntity{

    @Column(columnDefinition = "varchar(30)")
    private String name;

    @ManyToOne(cascade= CascadeType.REFRESH)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.REFRESH},fetch = FetchType.EAGER)
    private Set<Logistics> logisticsSet;

    @Column(columnDefinition = "int")
    private ShopStatus status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public ShopStatus getStatus() {
        return status;
    }

    public void setStatus(ShopStatus status) {
        this.status = status;
    }

    public Set<Logistics> getLogisticsSet() {
        return logisticsSet;
    }

    public void setLogisticsSet(Set<Logistics> logisticsSet) {
        this.logisticsSet = logisticsSet;
    }
}
