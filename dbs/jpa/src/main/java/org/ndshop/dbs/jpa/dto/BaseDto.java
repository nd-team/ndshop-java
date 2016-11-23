package org.ndshop.dbs.jpa.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [基础数据传输，所有dto继承该类]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class BaseDto extends PageDto implements Serializable {
    private static final long serialVersionUID = -3558525794931360478L;
    private List<String> sorts; //排序字段 (有排序字段默认排序)
    private String order = "desc"; //默认排序方式
    private List<Condition> conditions = new ArrayList<Condition>(0);// 类搜索条件


    public List<String> getSorts() {
        return sorts;
    }

    public void setSorts(List<String> sorts) {
        this.sorts = sorts;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }
}
