package test_java_service.code.dao;


import com.dounine.corgi.jpa.dao.MyRep;
import test_java_service.code.dto.UserDto;
import test_java_service.code.dto.UserInterestDto;
import test_java_service.code.entity.User;
import test_java_service.code.entity.UserInterest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Created by huanghuanlai on 16/5/24.
 */
public interface IUserInterestRep extends MyRep<UserInterest,UserInterestDto> {

}
