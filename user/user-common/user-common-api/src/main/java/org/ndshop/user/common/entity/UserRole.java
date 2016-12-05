package org.ndshop.user.common.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.ndshop.dbs.jpa.enums.Status;

import javax.persistence.*;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户角色]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "user_user_role")
public class UserRole extends BaseEntity {
    @ManyToOne( cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne( cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;


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

}
