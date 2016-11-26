package org.ndshop.user.common.service;


import org.apache.commons.lang3.StringUtils;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
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

import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [角色业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class RoleSerImpl extends ServiceImpl<Role, RoleDto> implements IRoleSer {

    @Autowired
    private IUserRoleSer userRoleSer;

    @Transactional
    @Override
    public Optional<Role> save(Role role) throws SerException {
        String parent_id = role.getParent().getId();
        if (StringUtils.isNotBlank(parent_id)) { //假如有父节点
            Optional<Role> op_parent = findById(parent_id);
            if (op_parent.isPresent()) {
                role.setParent(op_parent.get());
            }
        }
        return super.save(role);
    }

    @Transactional
    @Override
    public void update(Role role) throws SerException {
        Optional<Role> op_parent = Optional.ofNullable(role.getParent()); //
        if (op_parent.isPresent()) {
            if (role.getId().equals(role.getParent().getId())) {
                throw new SerException("上级不能选择自己");
            }
            if (isChildren(op_parent.get().getId(), role)) {
                throw new SerException("上级不能为自己的下级");
            }
            op_parent = findById(role.getParent().getId());//更新父节点
        }
        role.setParent(op_parent.get());
        super.update(role);

    }

    @Cacheable("userSerCache")
    @Override
    public Optional<List<Role>> findChildByParentId(String parent_id) throws SerException {
        RoleDto dto = new RoleDto();
        Condition codn = new Condition("id", DataType.STRING, parent_id);
        codn.fieldToModels(Role.class);
        dto.getConditions().add(codn);
        return findByCis(dto);
    }


    /**
     * @param parent_id 要更改的父亲节点
     * @param current   当前节点
     * @return
     * @throws SerException
     */
    private boolean isChildren(String parent_id, Role current) throws SerException {
        //查询当前节点的孩子节点
        Optional<List<Role>> op_children = this.findChildByParentId(current.getId());
        if (op_children.isPresent()) {
            if (op_children.get().stream().anyMatch(permission -> permission.getId().equals(parent_id))) {
                return Boolean.TRUE;
            }
        }

        for (Role role : op_children.get()) { //递归遍历所有子孙节点
            if (isChildren(parent_id, role)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @Cacheable("userSerCache")
    @Override
    public Optional<Set<Role>> findChildByRoleId(String roleId) throws SerException {
        Set<Role> allChild = new HashSet<>();
        initAllChild(roleId, allChild);
        return Optional.ofNullable(allChild);
    }

    private void initAllChild(String roleId, Set<Role> allChild) throws SerException {
        //查询当前节点的所有孩子节点
        Set<Role> temp_child = new HashSet<>();
        Optional<List<Role>> op_children = this.findChildByParentId(roleId);
        if (op_children.isPresent()) {
            op_children.get().stream().forEach(role -> {
                if (role.getParent().getId().equals(roleId)) {
                    temp_child.add(role);
                }
            });
        }

        if (null != temp_child && temp_child.size() > 0) {
            allChild.addAll(temp_child);
            for (Role child : temp_child) {
                initAllChild(child.getId(), allChild);
            }
        }
    }

    @Cacheable("userSerCache")
    @Override
    public Optional<Set<Role>> findRoleByUserId(String userId) throws SerException {
        Set<Role> roles = new HashSet<>();
        Optional<List<UserRole>> op_userRoles = userRoleSer.findByUserId(userId);
        if (op_userRoles.isPresent()) {
            op_userRoles.get().stream().forEach(userRole -> {
                roles.add(userRole.getRole());
            });
        }
        return Optional.ofNullable(roles);
    }
}
