package org.ndshop.user.common.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.ndshop.dbs.jpa.enums.Status;

import javax.persistence.*;

/**
 * Created by lgq on 16-11-22.
 * 认证
 */
@Entity
@Table(name = "user_permission")
public class Permission extends BaseEntity {

    private String name;//认证名
    @Column(unique = true)
    private String resource;//认证资源
    private String description;//描述
    private Status status;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Permission parent;//认证父id

    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role; //所属

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Permission getParent() {
        return parent;
    }

    public void setParent(Permission parent) {
        this.parent = parent;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
