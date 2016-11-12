package test_java_service.code.dao;


import org.ndshop.dbs.jpa.dao.MyRep;
import test_java_service.code.dto.UserInterestDto;
import test_java_service.code.entity.UserInterest;

/**
 * Created by huanghuanlai on 16/5/24.
 */
public interface IUserInterestRep extends MyRep<UserInterest,UserInterestDto> {

}
