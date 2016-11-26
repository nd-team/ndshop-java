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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Permission save(Permission permission) throws SerException {
        String parent_id = permission.getParent().getId();
        if (StringUtils.isNotBlank(parent_id)) { //假如有父节点
            Permission parent = findById(parent_id);
            if (null != parent) {
                permission.setParent(parent);
            }
        }
        return super.save(permission);
    }

    @Transactional
    @Override
    public void update(Permission permission) throws SerException {
        Permission parent = permission.getParent(); //
        if (null != parent) {
            if (permission.getParent().getId().equals(permission.getId())) {
                throw new SerException("上级不能选择自己");
            }
            if (isChildren(parent.getId(), permission)) {
                throw new SerException("上级不能为自己的下级");
            }
            parent = findById(permission.getParent().getId());//更新父节点
        }
        permission.setParent(parent);
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
        List<Permission> children = this.findChildByParentId(current.getId());
        if (null != children && children.size() > 0) {
            if (children.stream().anyMatch(permission -> permission.getId().equals(parent_id))) {
                return Boolean.TRUE;
            }
        }

        for (Permission permission : children) { //递归遍历所有子孙节点
            if (isChildren(parent_id, permission)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }


    @Cacheable("userSerCache")
    @Override
    public Set<Permission> findAllByUserId(String userId) throws SerException {
        List<UserRole> userRoles = userRoleSer.findByUserId(userId);//所拥有的角色
        Set<Permission> permissions = new HashSet<>(); //所有认证权限
        if (null != userRoles && userRoles.size() > 0) {
            userRoles.stream().forEach(userRole -> {
                Role role = userRole.getRole();
                if (null != role) {
                    permissions.addAll(role.getPermissions()); //添加角色拥有认证权限到集合
                }

            });
        }

        return permissions;
    }

    @Override
    public Set<Permission> findByRoleId(String roleId) throws SerException {
        Set<Role> roles = roleSer.findChildByRoleId(roleId);
        Set<Permission> permissions = new HashSet<>(); //所有认证权限
        if (null != roles && roles.size() > 0) {
            roles.stream().forEach(role -> {
                permissions.addAll(role.getPermissions());
            }); //添加角色拥有认证权限到集合
        }
        return permissions;
    }

    @Override
    public List<Permission> findChildByParentId(String parent_id) throws SerException {
        PermissionDto dto = new PermissionDto();
        Condition codn = new Condition("id", DataType.STRING, parent_id);
        codn.fieldToModels(Permission.class);
        dto.getConditions().add(codn);
        return findByCis(dto);
    }
}
