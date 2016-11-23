package test_java_service.code.service;

import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test_java_service.code.dao.IUserGroupRep;
import test_java_service.code.dto.UserGroupDto;
import test_java_service.code.entity.UserGroup;

import java.util.Optional;

/**
 * Created by lgq on 16-10-13.
 */
@Service
public class UserGroupSerImpl extends ServiceImpl<UserGroup, UserGroupDto> implements IUserGroupSer {
    @Autowired
    private IUserGroupRep groupRep;
    @Override
    public Optional<UserGroup> findByName(String name) {
        return groupRep.findByName(name);
    }
}
