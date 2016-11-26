package org.ndshop.user.common.service;


import org.apache.commons.lang3.StringUtils;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.user.common.dto.PermissionDto;
import org.ndshop.user.common.entity.Permission;
import org.ndshop.user.common.entity.Role;
import org.ndshop.user.common.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
    private IUserRoleSer userRoleSer;
    @Autowired
    private IRoleSer roleSer;

    @Transactional
    @Override
    public Optional<Permission> save(Permission permission) throws SerException {
        String parent_id = permission.getParent().getId();
        if (StringUtils.isNotBlank(parent_id)) { //假如有父节点
            Optional<Permission> op_parent = findById(parent_id);
            if (op_parent.isPresent()) {
                permission.setParent(op_parent.get());
            }
        }
        return super.save(permission);
    }

    @Transactional
    @Override
    public void update(Permission permission) throws SerException {
        Optional<Permission> op_parent = Optional.ofNullable(permission.getParent()); //
        if (op_parent.isPresent()) {
            if (permission.getParent().getId().equals(permission.getId())) {
                throw new SerException("上级不能选择自己");
            }
            if (isChildren(op_parent.get().getId(), permission)) {
                throw new SerException("上级不能为自己的下级");
            }
            op_parent = findById(permission.getParent().getId());//更新父节点
        }
        permission.setParent(op_parent.get());
        super.update(permission);

    }

    /**
     * @param parent_id 要更改的父亲节点
     * @param current   当前节点
     * @return
     * @throws SerException
     */
    private boolean isChildren(String parent_id, Permission current) throws SerException {
        //查询当前节点的孩子节点
        Optional<List<Permission>> op_children = this.findChildByParentId(current.getId());
        if (op_children.isPresent()) {
            if (op_children.get().stream().anyMatch(permission -> permission.getId().equals(parent_id))) {
                return Boolean.TRUE;
            }
        }

        for (Permission permission : op_children.get()) { //递归遍历所有子孙节点
            if (isChildren(parent_id, permission)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Cacheable("userSerCache")
    @Transactional
    @Override
    public Optional<Set<Permission>> findAllByUserId(String userId) throws SerException {
        Optional<List<UserRole>> op_userRole = userRoleSer.findByUserId(userId);//所拥有的角色
        Set<Permission> permissions = new HashSet<>(); //所有认证权限
        if (op_userRole.isPresent()) {
            op_userRole.get().stream().forEach(userRole -> {
                Role role = userRole.getRole();
                if (null != role) {
                    permissions.addAll(role.getPermissions()); //添加角色拥有认证权限到集合
                }

            });
        }

        return Optional.ofNullable(permissions);
    }

    @Cacheable("userSerCache")
    @Override
    public Optional<Set<Permission>> findByRoleId(String roleId) throws SerException {
        Optional<Set<Role>> op_roles = roleSer.findChildByRoleId(roleId);
        Set<Permission> permissions = new HashSet<>(); //所有认证权限
        if (op_roles.isPresent()) {
            op_roles.get().stream().forEach(role -> {
                permissions.addAll(role.getPermissions());
            }); //添加角色拥有认证权限到集合
        }
        return  Optional.ofNullable(permissions);
    }

    @Cacheable("userSerCache")
    @Override
    public Optional<List<Permission>> findChildByParentId(String parent_id) throws SerException {
        PermissionDto dto = new PermissionDto();
        Condition codn = new Condition("id", DataType.STRING, parent_id);
        codn.fieldToModels(Permission.class);
        dto.getConditions().add(codn);
        return findByCis(dto);
    }
}
