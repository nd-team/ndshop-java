package org.ndshop.shop.entity;

import com.dounine.corgi.entity.BaseEntity;
import org.ndshop.user.common.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by ike on 16-11-9.
 */
@Entity
@Table(name = "nd_shopComment")
public class ShopComment extends BaseEntity{

    @Column(columnDefinition = "TIMESTAMP",nullable = false)
    private LocalDateTime commentTime;

    @ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    @Column(nullable = false)
    private User user;

    @Column(name="comment",nullable = false)
    private String comment;

    public LocalDateTime getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(LocalDateTime commentTime) {
        this.commentTime = commentTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
