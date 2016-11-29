package org.ndshop.goods.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-23 17:38]
 * @Description: [商品分类]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "goodsCategory")
public class GoodsCategory extends BaseEntity {

    private String goodsCategoryNum ;//分类编号

    @Column(nullable = false, unique = true)
    private String name; //分类名

    @Column(nullable = false )
    private Long parentNodeNum ;//拥有父级个数

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private GoodsCategory parent ;//分类父id

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(columnDefinition="dateTime")
    private LocalDateTime createTime = LocalDateTime.now();//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(columnDefinition="dateTime")
    private LocalDateTime modifyTime = LocalDateTime.now();//修改时间

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "goodsCategory" , fetch = FetchType.LAZY)
    private Set<Goods> good ;

    public String getGoodsCategoryNum() {
        return goodsCategoryNum;
    }

    public void setGoodsCategoryNum(String goodsCategoryNum) {
        this.goodsCategoryNum = goodsCategoryNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentNodeNum() {
        return parentNodeNum;
    }

    public void setParentNodeNum(Long parentNodeNum) {
        this.parentNodeNum = parentNodeNum;
    }

    public GoodsCategory getParent() {
        return parent;
    }

    public void setParent(GoodsCategory parent) {
        this.parent = parent;
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

    public Set<Goods> getGood() {
        return good;
    }

    public void setGood(Set<Goods> good) {
        this.good = good;
    }
}

