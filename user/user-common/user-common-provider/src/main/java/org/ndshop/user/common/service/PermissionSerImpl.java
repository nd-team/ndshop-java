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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [权限认证业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
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
    public Optional<Set<Permission>> findAllByUserId(String userId) throws SerException {
        Optional<List<UserRole>> optional = userRoleSer.findByUserId(userId);//所拥有的角色
        Set<Permission> permissions = new HashSet<>(); //所有认证权限
        if(optional.isPresent()){
            Stream<UserRole> userRoleStream = optional.get().stream();
            userRoleStream.forEach(userRole -> {
                Role role = userRole.getRole();
                if (null != role) {
                    permissions.addAll(role.getPermissions());
                }

            });
        }

        return Optional.ofNullable(permissions);
    }

    @Transactional
    @Override
    public void addPermission() throws SerException {
        Optional<Role> optional = roleSer.findOne(new RoleDto());
        if(optional.isPresent()){
            Permission root = new Permission();
            root.setRole(optional.get());
            root.setDescription("无描述");
            root.setResource("/");
            root.setName("根资源");
            Permission child = new Permission();
            child.setRole(optional.get());
            child.setDescription("无描述");
            child.setResource("/user");
            child.setName("用户支援");
            child.setParent(root);
            save(child);
        }

    }

}
