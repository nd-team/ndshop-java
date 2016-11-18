package org.ndshop.user.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.ndshop.dbs.jpa.entity.BaseEntity;

import javax.persistence.*;

/**
 * Created by lgq on 16-10-26.
 * 用户详情
 */
@Entity
@Table(name="user_detail")
public class UserDetail extends BaseEntity {

    @OneToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name="user_id")
    @JSONField(serialize = false)
    private User user;

    private String address;//地址
    private String realName;//真实姓名
    private String birthday;//出生年月
    private String domicile;//居住地
    private String hometown;//家乡
    private Double growthValue; //成长值
    private String idCard;//身份证
    private String educationLevel;//教育程度
    private String job ; //职业

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean maritalStatus;//婚姻状态

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public Double getGrowthValue() {
        return growthValue;
    }

    public void setGrowthValue(Double growthValue) {
        this.growthValue = growthValue;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Boolean getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Boolean maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}
