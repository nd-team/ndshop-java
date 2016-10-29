package com.bjike.ndshop.user.entity;

import com.dounine.corgi.jpa.entity.BaseEntity;
import com.dounine.corgi.jpa.enums.Status;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

/**
 * Created by lgq on 16-10-26.
 * 收货地址
 */
@Entity
@Table(name = "nd_shipping_address")
public class ShippingAddress extends BaseEntity{
    @ManyToOne(cascade = {CascadeType.ALL},optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;//所属用户

    @Column(nullable = false)
    private String area; //地区
    @Column(nullable = false)
    private String detailAddress;//详细地址
    @Email
    private String email;//邮件
    private String postcode;//邮政编码
    @Column(length = 25,nullable = false)
    private String receiverName;//收货人姓名
    private String phone ; // 手机号码（手机号码电话号码二选一必填）
    private String telPhone;//电话号码
    private Boolean defaultAddress; //是否默认地址
    @Range(min = 0,max = 20)
    private Integer seq;// 序列 0-20
    private Status status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public Boolean getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
