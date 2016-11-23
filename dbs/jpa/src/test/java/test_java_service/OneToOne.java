package test_java_service;

import com.alibaba.fastjson.JSON;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test_java_service.code.ApplicationConfiguration;
import test_java_service.code.dto.UserDto;
import test_java_service.code.entity.User;
import test_java_service.code.entity.UserInfo;
import test_java_service.code.service.IUserSer;

import java.util.Optional;

/**
 * Created by huanghuanlai on 2016/10/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class OneToOne {

    /**
     * 主控方来维持对象关系
     * mappedBy = "user" User 为主控方，维持userInfo（被控方关系）
     */

    @Autowired
    private IUserSer userSer;


    @Before
    public void init() throws SerException {
        if (null == userSer.findByUsername("liguiqin")) {
            User user = new User();
            user.setUsername("liguiqin");
            user.setPassword("123456");
            user.setMoney(5000.0);
            user.setAge(55);
            user.setHeight(1.2f);
        }
    }

    @Test
    public void addUserInfo() throws SerException {
        Optional<User> optional = userSer.findByUsername("liguiqin");
        UserInfo info = optional.get().getUserInfo();
        if (null == info) {
            info = new UserInfo();
        }
        info.setEmail("xinaml@qq.com");
        info.setFox("1122");
        info.setUser(optional.get());
        optional.get().setUserInfo(info);
        userSer.update(optional.get());
        System.out.println(JSON.toJSONString(optional.get()));
    }

    @Test
    public void findByEmail() throws SerException {
        UserDto dto = new UserDto();
        Condition condition = new Condition(UserInfo.class.getName()+"#email", DataType.STRING,"xinaml@qq.com");
        condition.setRestrict(RestrictionType.EQ);
        dto.getConditions().add(condition);
        System.out.println(userSer.findOne(dto));

    }



}
