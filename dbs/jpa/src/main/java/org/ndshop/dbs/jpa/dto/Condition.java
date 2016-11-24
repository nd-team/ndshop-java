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
    //单表查询直接用属性名，连表查询格式为（com.bjike.ndshop.UserInfo#id）
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

    /**
     * 连表查询属性必调该方法
     * @param clazz　连接表对象
     */
    public void fieldToModels(Class... clazz) {
        StrBuilder sb = new StrBuilder();
        for(Class c :clazz){
            sb.append(c.getName());
            sb.append("#");
        }
        StrBuilder result = new StrBuilder();
        result.append( sb.substring(0,sb.length()-1));
        result.append("#");
        result.append(this.getField());
        this.setField(result.toString());
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
