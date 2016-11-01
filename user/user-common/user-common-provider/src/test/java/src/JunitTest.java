package src;

import com.bjike.ndshop.user.common.api.entity.User;
import com.bjike.ndshop.user.common.api.entity.UserDetail;
import com.bjike.ndshop.user.common.api.enums.MemberType;
import com.bjike.ndshop.user.common.api.service.IUserSer;
import com.dounine.corgi.jpa.enums.Status;
import com.dounine.corgi.jpa.exception.SerException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import src.com.bjike.ndshop.user.common.ApplicationConfiguration;


import java.util.List;

/**
 * Created by huanghuanlai on 2016/10/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class JunitTest {

    /**
     * 基础增删改查，批量操作等
     */

    @Autowired
    private IUserSer userSer;


    @Before
    public void init() throws SerException {
        if (null == userSer.findByUsername("liguiqin")) {
            User user = new User();
            user.setUsername("liguiqin");
            user.setPassword("123456");
            user.setNickname("xiaoming");
            user.setMemberType(MemberType.BRONZE);
            user.setSex('男');
            user.setPhone("110");
            user.setStatus(Status.CONGEAL);
            user.setEmail("xinaml");
            UserDetail detail = new UserDetail();
            detail.setRealName("111");
            detail.setUser(user);
            detail.setAddress("南宁");
            user.setDetail(detail);
            userSer.save(user);
        }
    }

    /**
     * 查询全部
     */
    @Test
    public void findAll() throws SerException {
        List<User> users = userSer.findAll();
        for (User u : users) {
            System.out.println(u.getUsername());
        }
    }



}
