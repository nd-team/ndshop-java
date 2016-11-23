package org.ndshop.user.common.service;

import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.user.common.dto.UserDto;
import org.ndshop.user.common.entity.User;
import org.ndshop.dbs.jpa.exception.SerException;

import java.util.Optional;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户业务接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IUserSer extends IService<User, UserDto> {


    default Optional<User> findByUsername(String username) throws SerException {
        return null;
    }

    default Optional<User> findByNickname(String nickname) throws SerException {
        return null;
    }

    default Optional<User> findByPhone(String phone) throws SerException {
        return null;
    }

    /**
     * 验证账号（邮箱/电话号码/用户名）
     * cause by findpassword
     *
     * @param accountNumber
     * @return
     * @throws SerException
     */
    default Optional<User> findByAccountNumber(String accountNumber) throws SerException {
        return null;
    }


}
