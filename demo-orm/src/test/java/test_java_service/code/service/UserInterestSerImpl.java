package test_java_service.code.service;

import com.dounine.corgi.jpa.service.ServiceImpl;
import test_java_service.code.dto.UserGroupDto;
import test_java_service.code.dto.UserInterestDto;
import test_java_service.code.entity.UserGroup;
import test_java_service.code.entity.UserInterest;
import org.springframework.stereotype.Service;

/**
 * Created by lgq on 16-10-13.
 */
@Service
public class UserInterestSerImpl extends ServiceImpl<UserInterest, UserInterestDto> implements IUserInterestSer {

}
