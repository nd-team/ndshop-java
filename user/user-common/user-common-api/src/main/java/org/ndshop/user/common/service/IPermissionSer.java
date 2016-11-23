package org.ndshop.user.common.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.user.common.dto.PermissionDto;
import org.ndshop.user.common.entity.Permission;
import org.ndshop.user.common.entity.User;

import java.util.Set;

/**
 * Created by lgq on 16-11-22.
 */
public interface IPermissionSer extends IService<Permission, PermissionDto> {
    void addPermission() throws SerException;

    Set<Permission> findAllByUserId(String userId) throws SerException;
}
