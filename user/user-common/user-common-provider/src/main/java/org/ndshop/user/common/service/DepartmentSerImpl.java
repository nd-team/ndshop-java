package org.ndshop.user.common.service;


import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.user.common.dto.DepartmentDto;
import org.ndshop.user.common.entity.Department;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


/**
 * @Author: [liguiqin]
 * @Date: [2016-12-28 15:47]
 * @Description: [部门业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@CacheConfig(cacheNames = "userSerCache")
@Service
public class DepartmentSerImpl extends ServiceImpl<Department, DepartmentDto> implements IDepartmentSer {

}
