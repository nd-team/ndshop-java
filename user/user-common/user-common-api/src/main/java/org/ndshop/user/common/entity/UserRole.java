package org.ndshop.user.common.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.ndshop.dbs.jpa.enums.Status;

import javax.persistence.*;

/**
 * Created by lgq on 16-11-22.
 * 用户角色
 */
@Entity
@Table(name = "user_user_role")
public class UserRole extends BaseEntity {
    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "role_id")
    private Role role;

    private Status status=Status.THAW;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
