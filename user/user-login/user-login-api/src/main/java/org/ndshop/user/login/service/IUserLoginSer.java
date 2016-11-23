package org.ndshop.user.login.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.user.common.dto.UserDto;
import org.ndshop.user.common.entity.User;
/**
 * Created by lgq on 16-10-31.
 */
public interface IUserLoginSer extends IService<User,UserDto> {

    /**
     * 验证 token
     * @param token
     * @return
     * @throws SerException
     */
    boolean verify(String token) throws SerException;

    /**
     *登录
     * @param user
     * @return token
     * @throws SerException
     */
    String login(User user) throws SerException;

    /**
     *登录
     * @param token
     * @return token
     * @throws SerException
     */
    boolean loginOut(String token) throws SerException;

}
