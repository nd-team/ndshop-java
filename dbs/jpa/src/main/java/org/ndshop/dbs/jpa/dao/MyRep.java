package org.ndshop.dbs.jpa.dao;

import org.ndshop.dbs.jpa.dto.BaseDto;
import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [jpa dao接口，实现该接口可使用ｊｐａ命名查询]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface MyRep<BE extends BaseEntity, BD extends BaseDto> extends JpaRepository<BE, String>
        , JpaSpecificationExecutor<BE> {

    Optional<BE> findById(String id);

}
