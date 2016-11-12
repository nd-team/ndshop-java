import org.ndshop.user.common.enums.SexType;
import org.junit.Before;
import user_common_code.ApplicationConfiguration;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.service.IUserSer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by huanghuanlai on 2016/10/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class UserTest {

    @Before
    public void init() throws SerException {
        if (null == userSer.findByUsername("liguiqin")) {
            User user = new User();
            user.setUsername("liguiqin");
            user.setPassword("123456");
            user.setAge(55);
            user.setNickname("xiaoming");
            user.setEmail("xinaml@qq.com");
            user.setSex(SexType.MAN);
            user.setPhone("13457910241");
            userSer.save(user);
        }
    }

    @Autowired
    private IUserSer userSer;


    /**
     * 查询全部
     */
    @Test
    public void findAll() throws SerException {
        List<User> users = userSer.findAll();
        for(User u : users){
            System.out.println(u.getUsername());
        }
    }

    /**
     * 通过用户姓名邮件手机号查找用户
     * @throws SerException
     */
    @Test
    public void verifyByAccountNumber() throws SerException {
        System.out.println(null!=userSer.findByAccountNumber("1"));

    }




}
