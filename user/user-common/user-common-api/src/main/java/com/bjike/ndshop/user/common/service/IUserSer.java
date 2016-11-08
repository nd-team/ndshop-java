package com.bjike.ndshop.user.common.service;

import com.bjike.ndshop.dbs.jpa.service.IService;
import com.bjike.ndshop.user.common.dto.UserDto;
import com.bjike.ndshop.user.common.entity.User;
import com.bjike.ndshop.dbs.jpa.exception.SerException;

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
