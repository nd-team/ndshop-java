package test_java_service.code.service;



import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.springframework.cache.annotation.Cacheable;
import test_java_service.code.dto.UserDto;
import test_java_service.code.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by huanghuanlai on 16/5/24.
 */
public interface IUserSer extends IService<User, UserDto> {

    @Cacheable("serviceCache")
    Optional<User> findByUsername(String username) throws SerException;

    Optional<User> findByNickname(String nickname) throws SerException;


}
