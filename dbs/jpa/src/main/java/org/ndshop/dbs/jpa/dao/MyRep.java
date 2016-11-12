package org.ndshop.dbs.jpa.dao;

import org.ndshop.dbs.jpa.dto.BaseDto;
import org.ndshop.dbs.jpa.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by huanghuanlai on 16/9/3.
 */
public interface MyRep<BE extends BaseEntity,BD extends BaseDto> extends JpaRepository<BE,String>
        ,JpaSpecificationExecutor<BE> {

    default BE findById(String id){
        return findOne(id);
    }

}
