package test_java_service.code.service;

import com.dounine.corgi.jpa.service.IService;
import test_java_service.code.dto.UserInterestDto;
import test_java_service.code.entity.UserInterest;

/**
 * Created by lgq on 16-10-25.
 */
public interface IUserInterestSer extends IService<UserInterest, UserInterestDto> {
}
