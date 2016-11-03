package com.bjike.ndshop.user.common.entity;

import com.bjike.ndshop.dbs.jpa.entity.BaseEntity;
import com.bjike.ndshop.dbs.jpa.enums.Status;
import com.bjike.ndshop.user.common.enums.MemberType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by lgq on 16-10-26.
 */
@Entity
@Table(name = "nd_user")
public class User extends BaseEntity {

    @Column(unique = true, length = 16, nullable = false)
    private String username; //登录用户名

    @Column(unique = true, length = 11, nullable = false)
    private String phone;//登录手机(注册验证手机)

    @Email
    private String email;//登录邮箱

    @Column(nullable = false)
    private String password; //登陆密码

    private String headSculpture;//头像

    @Column(unique = true)
    private String nickname; //昵称

    @Column(length = 1)
    private char sex;//性别

    @Range(min = 0, max = 120)
    private Integer age;//年龄

    private MemberType memberType; //会员类型

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime accessTime = LocalDateTime.now(); //链接时间

    private LocalDateTime createTime = LocalDateTime.now();//创建时间

    private Status status = Status.THAW;//用户状态

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private UserDetail detail; //用户详情

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadSculpture() {
        return headSculpture;
    }

    public void setHeadSculpture(String headSculpture) {
        this.headSculpture = headSculpture;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserDetail getDetail() {
        return detail;
    }

    public void setDetail(UserDetail detail) {
        this.detail = detail;
    }
}
