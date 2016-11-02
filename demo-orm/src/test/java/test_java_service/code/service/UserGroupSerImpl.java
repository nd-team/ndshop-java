package test_java_service.code.service;

import com.dounine.corgi.jpa.service.ServiceImpl;
import test_java_service.code.dao.IUserGroupRep;
import test_java_service.code.dto.UserGroupDto;
import test_java_service.code.entity.User;
import test_java_service.code.entity.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lgq on 16-10-13.
 */
@Service
public class UserGroupSerImpl extends ServiceImpl<UserGroup, UserGroupDto> implements IUserGroupSer {
    @Autowired
    private IUserGroupRep groupRep;
    @Override
    public UserGroup findByName(String name) {
        return groupRep.findByName(name);
    }
}
