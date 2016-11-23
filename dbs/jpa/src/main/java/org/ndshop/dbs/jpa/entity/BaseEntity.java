package org.ndshop.dbs.jpa.entity;

import com.dounine.corgi.validation.Del;
import com.dounine.corgi.validation.Edit;
import com.dounine.corgi.validation.Get;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [基础实体类，所有entity继承该类]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region ="queryCache" )
public abstract class BaseEntity implements Serializable{
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false,length = 36)
    @NotBlank(message = "user id not blank",groups = {Del.class, Edit.class, Get.class})
    protected String id;//entityｉｄ

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
