package org.ndshop.goods.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-28 14:30]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "goodsFieldValue")
public class GoodsFieldsValue extends BaseEntity{

    private String fieldValue ;//扩展字段值

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime = LocalDateTime.now();//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime modifyTime = LocalDateTime.now();//修改时间

    @ManyToOne(cascade ={CascadeType.REFRESH,CascadeType.REMOVE} )
    @JoinColumn(name = "goodsFields_id")
    private GoodsFields goodsFields;//扩展字段

    @OneToOne(cascade = CascadeType.REFRESH )
    @JoinColumn(name = "goods_id")
    @JSONField(serialize = false)
    private Goods goods; //商品

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
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

    public GoodsFields getGoodsFields() {
        return goodsFields;
    }

    public void setGoodsFields(GoodsFields goodsFields) {
        this.goodsFields = goodsFields;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
