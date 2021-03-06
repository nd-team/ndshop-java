package org.ndshop.user.common.service;

import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.user.common.dto.UserDto;
import org.ndshop.user.common.entity.User;
import org.ndshop.dbs.jpa.exception.SerException;

/**
 * Created by lgq on 16-10-28.
 */
public interface IUserSer extends IService<User, UserDto> {


    default User findByUsername(String username)throws SerException{
        return null;
    }

    default User findByNickname(String nickname)throws SerException{
        return null;
    }

    default User findByPhone(String phone)throws SerException{
        return null;
    }

    /**
     * 验证账号（邮箱/电话号码/用户名）
     * cause by findpassword
     * @param accountNumber
     * @return
     * @throws SerException
     */
    default User findByAccountNumber(String accountNumber)throws SerException {
        return null;
    }


}
