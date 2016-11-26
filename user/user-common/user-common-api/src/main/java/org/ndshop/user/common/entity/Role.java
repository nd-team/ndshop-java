package org.ndshop.user.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.ndshop.dbs.jpa.enums.Status;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户角色]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "user_role")
public class Role extends BaseEntity {
    @Column(unique = true)
    private String name;//角色名
    private String description;//描述
    private Status status;//状态
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(columnDefinition = "dateTime")//指定数据库类型
    private LocalDateTime createTime = LocalDateTime.now();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Role role;//父角色

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
     @JoinTable(name="user_role_permission",joinColumns={@JoinColumn(name="role_id")},
            inverseJoinColumns={@JoinColumn(name="permission_id")})
    private Set<Permission> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role getParent() {
        return role;
    }

    public void setParent(Role parent) {
        this.role = parent;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
