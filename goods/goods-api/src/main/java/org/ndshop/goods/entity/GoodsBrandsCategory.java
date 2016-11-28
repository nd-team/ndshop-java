package org.ndshop.goods.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-26 11:55]
 * @Description: [品牌类别名]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "goodsBrandsCategory")
public class GoodsBrandsCategory extends BaseEntity {

    @Column(unique = true , name = "brandsCategoryName")
    private String name ;//品牌类别名

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(columnDefinition="dateTime")
    private LocalDateTime createTime = LocalDateTime.now();//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(columnDefinition="dateTime")
    private LocalDateTime modifyTime = LocalDateTime.now();//修改时间

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "goodsBrandsCategory" ,fetch = FetchType.LAZY)
    private Set<GoodsBrands> goodsBrands;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<GoodsBrands> getGoodsBrands() {
        return goodsBrands;
    }

    public void setGoodsBrands(Set<GoodsBrands> goodsBrands) {
        this.goodsBrands = goodsBrands;
    }
}
