package org.ndshop.goods.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-28 14:19]
 * @Description: [商品扩展字段表]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "goodsFields")
public class GoodsFields extends BaseEntity {

    private String fieldName ;//字段名

    private String fieldType ;//字段类型

    private String fieldDescription ;//字段描述

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime = LocalDateTime.now();//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime modifyTime = LocalDateTime.now();//修改时间

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "goodsFields",fetch = FetchType.LAZY)
    private Set<GoodsFieldsValue> goodsFieldsValue; //扩展字段值

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldDescription() {
        return fieldDescription;
    }

    public void setFieldDescription(String fieldDescription) {
        this.fieldDescription = fieldDescription;
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

    public Set<GoodsFieldsValue> getGoodsFieldsValue() {
        return goodsFieldsValue;
    }

    public void setGoodsFieldValue(Set<GoodsFieldsValue> goodsFieldsValue) {
        this.goodsFieldsValue = goodsFieldsValue;
    }
}
