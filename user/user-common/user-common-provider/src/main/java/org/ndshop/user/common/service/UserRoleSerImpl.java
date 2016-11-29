package org.ndshop.user.common.service;


import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.Status;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.user.common.dto.UserRoleDto;
import org.ndshop.user.common.entity.Role;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.entity.UserRole;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户角色业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class UserRoleSerImpl extends ServiceImpl<UserRole, UserRoleDto> implements IUserRoleSer {

    @Cacheable("userSerCache")
    @Override
    public List<UserRole> findByUserId(String userId) throws SerException {
        UserRoleDto dto = new UserRoleDto();

        Condition coin = new Condition("id", DataType.STRING, userId);
        coin.fieldToModels(User.class);
        dto.getConditions().add(coin);

        coin = new Condition(STATUS, DataType.ENUM, Status.THAW.getCode());
        coin.fieldToModels(Role.class);
        dto.getConditions().add(coin);
        return findByCis(dto);
    }
}
