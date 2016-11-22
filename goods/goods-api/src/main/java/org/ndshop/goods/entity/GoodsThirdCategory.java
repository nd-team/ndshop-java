package org.ndshop.goods.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by ike on 16-11-21.
 */
@Entity
@Table(name = "goodsThirdCategory")
public class GoodsThirdCategory extends BaseEntity{

    @Column(nullable = false ,unique = true)
    private  String thirdName ;
    @Column
    private String pinyin;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "goodsSecondCategory_id")
    private GoodsSecondCategory goodsSecondCategory;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition="dateTime") //指定数据库类型
    private LocalDateTime createTime = LocalDateTime.now();//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//格式化
    @Column(columnDefinition="dateTime") //指定数据库类型
    private LocalDateTime modifyTime = LocalDateTime.now();//修改时间

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public GoodsSecondCategory getGoodsSecondCategory() {
        return goodsSecondCategory;
    }

    public void setGoodsSecondCategory(GoodsSecondCategory goodsSecondCategory) {
        this.goodsSecondCategory = goodsSecondCategory;
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
