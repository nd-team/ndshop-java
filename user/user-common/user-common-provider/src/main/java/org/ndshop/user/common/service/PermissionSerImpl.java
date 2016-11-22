package org.ndshop.user.common.service;


import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.user.common.dto.PermissionDto;
import org.ndshop.user.common.dto.RoleDto;
import org.ndshop.user.common.entity.Permission;
import org.ndshop.user.common.entity.Role;
import org.ndshop.user.common.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by lgq on 16-11-22.
 */
@Service
public class PermissionSerImpl extends ServiceImpl<Permission, PermissionDto> implements IPermissionSer {
    @Autowired
    private IRoleSer roleSer;
    @Autowired
    private IUserRoleSer userRoleSer;

    @Cacheable("userSerCache")
    @Transactional
    @Override
    public Set<Permission> findAllByUserId(String userId) throws SerException {
        List<UserRole> userRoles = userRoleSer.findByUserId(userId);//所拥有的角色
        Set<Permission> permissions = new HashSet<>(); //所有认证权限
        Stream<UserRole> userRoleStream = userRoles.stream();
        userRoleStream.forEach(userRole -> {
            Role role = userRole.getRole();
            if (null != role) {
                permissions.addAll(role.getPermissions());
            }

        });
        return permissions;
    }

    @Transactional
    @Override
    public void addPermission() throws SerException {
        Role role = roleSer.findOne(new RoleDto());
        Permission root = new Permission();
        root.setRole(role);
        root.setDescription("无描述");
        root.setResource("/");
        root.setName("根资源");
        Permission child = new Permission();
        child.setRole(role);
        child.setDescription("无描述");
        child.setResource("/user");
        child.setName("用户支援");
        child.setParent(root);
        save(child);
    }

}
