package org.ndshop.user.common.service;


import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.user.common.dto.UserRoleDto;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.entity.UserRole;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lgq on 16-11-22.
 */
@Service
public class UserRoleSerImpl extends ServiceImpl<UserRole, UserRoleDto> implements IUserRoleSer {

    @Cacheable("userSerCache")
    @Override
    public List<UserRole> findByUserId(String userId) throws SerException {
        UserRoleDto dto = new UserRoleDto();
        Condition condition = new Condition("id", DataType.STRING,userId);
        condition.fieldToModels(User.class);
        dto.getConditions().add(condition);
        return findByCis(dto);
    }
}
