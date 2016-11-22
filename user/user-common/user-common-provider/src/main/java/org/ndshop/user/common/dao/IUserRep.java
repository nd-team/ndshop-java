package org.ndshop.user.common.dao;

import org.ndshop.dbs.jpa.dao.MyRep;
import org.ndshop.user.common.dto.UserDto;
import org.ndshop.user.common.entity.User;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by lgq on 16-10-28.
 */
public interface IUserRep extends MyRep<User, UserDto> {
    /**
     * 此处使用的是spring-data-jpa接口,不需要对接口进行实现,jpa可根据命名自动进行数据的查询
     * jpa接口规范：http://docs.spring.io/spring-data/jpa/docs/1.11.0.M1/reference/html/
     *
     * @param username,password 用户名
     * @return 用户信息
     */

    @Cacheable("userDaoCache")
    User findByUsernameAndPassword(String username, String password);

    @Cacheable("userDaoCache")
    User findByUsername(String username);

    @Cacheable("userDaoCache")
    User findByNickname(String nickname);
    @Cacheable("userDaoCache")
    User findByPhone(String phone);
}
