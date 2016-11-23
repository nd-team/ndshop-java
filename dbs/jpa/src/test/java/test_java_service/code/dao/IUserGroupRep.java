package test_java_service.code.dao;


import org.ndshop.dbs.jpa.dao.MyRep;
import test_java_service.code.dto.UserGroupDto;
import test_java_service.code.entity.UserGroup;

import java.util.Optional;

/**
 * Created by huanghuanlai on 16/5/24.
 */
public interface IUserGroupRep extends MyRep<UserGroup,UserGroupDto> {

    /**

     * @param name
     * @return
     */

    Optional<UserGroup> findByName(String name);
}
