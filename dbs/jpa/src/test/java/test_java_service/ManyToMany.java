package test_java_service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test_java_service.code.ApplicationConfiguration;
import test_java_service.code.entity.User;
import test_java_service.code.service.IUserSer;

import java.util.List;

/**
 * Created by huanghuanlai on 2016/10/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ManyToMany {
    /**
     * 双方共同维护关系 User Role  中间表 UserRole
     * 一般不会直接用manytomany,而是用中间表进行维护关系
     * 即 双向一对多来实现多对多
     */


    @Autowired
    private IUserSer userSer;

    @Test
    public void generateTable() {

    }

    @Test
    public void findUser() throws SerException {
        List<User> users = userSer.findAll();

        for (User u : users) {
            System.out.println(u.getInterests().size());
            System.out.println(u);
        }

    }


}
