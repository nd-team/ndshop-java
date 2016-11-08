package test_java_service.code.service;

import org.ndshop.dbs.jpa.service.IService;
import test_java_service.code.dto.UserGroupDto;
import test_java_service.code.entity.UserGroup;

/**
 * Created by lgq on 16-10-25.
 */
public interface IUserGroupSer extends IService<UserGroup, UserGroupDto> {
    UserGroup findByName(String name);
}
