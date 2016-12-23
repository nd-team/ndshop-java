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
     * field（字段） 包含 "." 则默认会设置成左连接，左连接set 集合 命名必须未Set结束
     * 如：Set<User>userSet List<User>userList
     */
    private String field; //字段
    private String values[];//字段值
    private DataType fieldType = DataType.STRING; //string int ...


    public Condition() {
    }

    public Condition(String field, DataType fieldType) {
        this.field = field;
        this.fieldType = fieldType;
    }

    public Condition(String field, DataType fieldType, Object value) {
        this.field = field;
        this.fieldType = fieldType;
        values = new String[]{String.valueOf(value)};
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

}
