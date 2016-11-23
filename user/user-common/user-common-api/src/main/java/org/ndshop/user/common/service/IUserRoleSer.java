package org.ndshop.user.common.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.user.common.dto.UserRoleDto;
import org.ndshop.user.common.entity.UserRole;

import java.util.List;

/**
 * Created by lgq on 16-11-22.
 */
public interface IUserRoleSer extends IService<UserRole, UserRoleDto> {
    List<UserRole> findByUserId(String userId) throws SerException;

}
