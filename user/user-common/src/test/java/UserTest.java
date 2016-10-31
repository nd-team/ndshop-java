import com.bjike.ndshop.user.common.ApplicationConfiguration;
import com.bjike.ndshop.user.common.entity.User;
import com.bjike.ndshop.user.common.enums.MemberType;
import com.bjike.ndshop.user.common.service.IUserSer;
import com.dounine.corgi.jpa.exception.SerException;
import com.dounine.corgi.security.PasswordHash;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by huanghuanlai on 2016/10/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class UserTest {

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
            try {
                user.setPassword(PasswordHash.createHash("123456"));

            } catch (Exception e) {
                throw new SerException(e.getMessage());
            }
            user.setAge(11);
            user.setNickname("昵称");
            user.setEmail("xinaml@qq.com");
            user.setPhone("110");
            user.setMemberType(MemberType.BRONZE);
            user.setSex('男');
            userSer.save(user);
        }
    }

    /**
     * 通过用户名查询用户
     */
    @Test
    public void findByUserName() throws SerException {
        User user = userSer.findByUsername("liguiqin");
        if (null != user) {
            System.out.println(user.getUsername());
        } else {
            System.out.println("not find user!");
        }
    }

    /**
     * 登录
     */
    @Test
    public void loginUser() throws SerException {
        String username = "liguiqin";
        String password = "123456";
        User user = userSer.findByUsername(username);
        if (null != user) {
            try {
                if (PasswordHash.validatePassword(password, user.getPassword())) {
                    System.out.println("login success");
                } else {
                    System.out.println("login fail");
                }
            } catch (Exception e) {
                throw new SerException(e.getMessage());
            }

        } else {
            System.out.println("login fail!");
        }
    }


}
