package org.ndshop.dbs.jpa.dto;


import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.apache.commons.lang3.text.StrBuilder;
import org.ndshop.dbs.jpa.enums.Status;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [查询条件传输]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class Condition {
    private RestrictionType restrict = RestrictionType.EQ; //查询条件 eq gt lt ...

    /**
     * field（字段） 包含 "." 则默认会设置成左连接，不再需要手动调用设置为leftJoin
     */

    private String field; //字段
    private String values[];//字段值
    private DataType fieldType = DataType.STRING; //string int ...

    private Boolean leftJoin =false; //是否有左连接表
    private Boolean leftJoinSet=false; //是否有左连接包含Sets为属性(条件)

    public Condition() {
    }

    public Condition(String field, DataType fieldType) {
        this.field = field;
        this.fieldType = fieldType;
    }

    public Condition(String field, DataType fieldType, Object value) {
        if(field.split("\\.").length>1){ //设置默认左连接
            leftJoin = true;
            leftJoinSet =false;
        }
        this.field = field;
        this.fieldType = fieldType;
        values = new String[]{String.valueOf(value)};
    }

    /**
     * 设置为左连接连接属性为set集合时，必须调用该方法查询
     */
    public void leftJoinSet() {
        leftJoin = false;
        leftJoinSet =true;
    }


    public RestrictionType getRestrict() {
        return restrict;
    }

    public void setRestrict(RestrictionType restrict) {
        this.restrict = restrict;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public DataType getFieldType() {
        return fieldType;
    }

    public void setFieldType(DataType fieldType) {
        this.fieldType = fieldType;
    }

    public Boolean isLeftJoin() {
        return leftJoin;
    }

    public Boolean isLeftJoinSet() {
        return leftJoinSet;
    }
}
