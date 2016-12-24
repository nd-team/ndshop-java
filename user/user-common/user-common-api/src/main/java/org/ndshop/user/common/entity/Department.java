package org.ndshop.user.common.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: [liguiqin]
 * @Date: [2016-12-24 10:16]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "user_department")
public class Department extends BaseEntity {
    @Column(unique = true,nullable = false)
    private String name;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
