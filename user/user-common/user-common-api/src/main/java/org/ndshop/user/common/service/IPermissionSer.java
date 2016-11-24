package org.ndshop.user.common.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.user.common.dto.PermissionDto;
import org.ndshop.user.common.entity.Permission;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [权限认证业务接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IPermissionSer extends IService<Permission, PermissionDto> {

    /**
     * 通过父节点查找其儿子节点
     * @param parent_id
     * @return
     * @throws SerException
     */
    default Optional<List<Permission>> findChildByParentId(String parent_id) throws SerException {
        return null;
    }

    /**
     * 通过用户id查询其所拥有的所有权限资源
     *
     * @param userId
     * @return
     * @throws SerException
     */
    default Optional<Set<Permission>> findAllByUserId(String userId) throws SerException {
        return null;
    }


}
