package org.ndshop.user.common.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.user.common.dto.RoleDto;
import org.ndshop.user.common.entity.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [角色业务接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IRoleSer extends IService<Role, RoleDto> {

    /**
     * 通过父节点查找其儿子节点(一级)
     * @param parent_id
     * @return
     * @throws SerException
     */
    default Optional<List<Role>> findChildByParentId(String parent_id) throws SerException {
        return null;
    }



    /**
     * 通过当前角色id查询其下的所有子孙节点
     * @param roleId
     * @return
     * @throws SerException
     */
    default Optional<Set<Role>> findChildByRoleId(String roleId) throws SerException {
        return null;
    }

    /**
     * 通过用户id查询其所有角色
     * @param userId
     * @return
     * @throws SerException
     */
    default Optional<Set<Role>> findRoleByUserId(String  userId) throws SerException {
        return null;
    }


}
