package org.ndshop.user.common.entity;

import org.ndshop.dbs.jpa.entity.BaseEntity;

import javax.persistence.*;

/**
 * 用户喜好
 * <p>
 * Created by lgq on 16-11-8.
 */
@Entity
@Table(name = "user_interest")
public class UserInterest extends BaseEntity {
    //用户
    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    //商品类型


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
