package org.ndshop.user.common.service;

import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.user.common.dto.UserDto;
import org.ndshop.user.common.entity.User;
import org.ndshop.dbs.jpa.exception.SerException;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by lgq on 16-10-28.
 */
public interface IUserSer extends IService<User, UserDto> {


    @Cacheable("serviceCache")
    default User findByUsername(String username) throws SerException {
        return null;
    }

    @Cacheable("serviceCache")
    default User findByNickname(String nickname) throws SerException {
        return null;
    }

    @Cacheable("serviceCache")
    default User findByPhone(String phone) throws SerException {
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
    @Cacheable("serviceCache")
    default User findByAccountNumber(String accountNumber) throws SerException {
        return null;
    }


}
