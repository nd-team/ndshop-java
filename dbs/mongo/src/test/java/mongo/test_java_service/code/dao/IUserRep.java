package mongo.test_java_service.code.dao;

import com.bjike.ndshop.dbs.mongo.dao.IRep;
import mongo.test_java_service.code.dto.UserDto;
import mongo.test_java_service.code.entity.User;

/**
 * Created by lgq on 16-10-17.
 */
public interface IUserRep extends IRep<User,UserDto> {

}
