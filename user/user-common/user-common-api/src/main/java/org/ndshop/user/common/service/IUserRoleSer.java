package org.ndshop.user.common.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.user.common.dto.UserRoleDto;
import org.ndshop.user.common.entity.UserRole;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户角色业务接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IUserRoleSer extends IService<UserRole, UserRoleDto> {
    /**
     * 通过用户id查询该用户拥有的角色
     *
     * @param userId
     * @return
     * @throws SerException
     */
    List<UserRole> findByUserId(String userId) throws SerException;

}
