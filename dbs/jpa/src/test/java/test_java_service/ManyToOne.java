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
import sun.security.acl.GroupImpl;
import test_java_service.code.ApplicationConfiguration;
import test_java_service.code.dto.UserDto;
import test_java_service.code.entity.User;
import test_java_service.code.entity.UserGroup;
import test_java_service.code.service.IUserGroupSer;
import test_java_service.code.service.IUserSer;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by huanghuanlai on 2016/10/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ManyToOne {
    /**
     * 双方共同维护关系
     * ManyToOne 指定 many 一方是不能独立存在的，否则存在孤儿数据
     * 一般一个group都要存在用户
     */

    @Autowired
    private IUserSer userSer;

    @Autowired
    private IUserGroupSer userGroupSer;

    @Before
    public void initGroup() throws SerException {
        Optional<List<UserGroup>> optional = userGroupSer.findAll();
        if (!optional.isPresent()) {
            UserGroup group1 = new UserGroup();
            group1.setName("用户组1");
            group1.setCreateTime(LocalDateTime.now());
            UserGroup group2 = new UserGroup();
            group2.setName("用户组2");
            group2.setCreateTime(LocalDateTime.now());
            userGroupSer.save(Arrays.asList(group1, group2));
        }

        if (null == userSer.findByUsername("liguiqin77")) {
            User user = new User();
            user.setUsername("liguiqin77");
            user.setPassword("123456");
            user.setMoney(5000.0);
            userSer.save(user);
        }
    }


    /**
     * 设置用户组到用户
     */
    @Test
    public void addGroupForUser() throws SerException {
        Optional<User> optional = userSer.findByUsername("liguiqin77");
        if(optional.isPresent()){
            User user = optional.get();
            user.setPassword("123456");
            user.setMoney(5000.0);
            Optional<UserGroup> optional1 = userGroupSer.findByName("用户组2");
            user.setGroup(optional1.get());
            userSer.update(user);
            System.out.println(JSON.toJSONString(user));
        }

    }


    /**
     * 删除被引用的用户组
     */
    @Test
    public void delGroupForUser() throws SerException {
        UserDto dto = new UserDto();
        Condition condition = new Condition("group.name", DataType.STRING);
        condition.setRestrict(RestrictionType.EQ);
        condition.setValues(new String[]{"用户组2"});
        Optional<List<User>> optional = userSer.findByCis(dto); //查询所有用户组2 的用户
        if(optional.isPresent()){
            for (User user : optional.get()) {
                user.setGroup(null);
            }
            userSer.update(optional.get());

        }

        Optional<UserGroup> optional1 = userGroupSer.findByName("用户组2");
        userGroupSer.remove(optional1.get());
    }


}
