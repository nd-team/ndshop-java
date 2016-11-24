import com.dounine.corgi.security.PasswordHash;
import org.ndshop.user.common.enums.SexType;
import org.junit.Before;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import user_common_code.ApplicationConfiguration;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.service.IUserSer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户业务测试]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class UserTest {

    @Autowired
    private IUserSer userSer;
    @Autowired
    CacheManager cacheManager;


    /**
     * 查询全部,缓存测试
     */
    @Test
    public void findAll() throws SerException {
        Optional<List<User>> op_users = userSer.findAll();
        op_users = userSer.findAll();
        op_users = userSer.findAll();

        Collection<String> cacheNames = cacheManager.getCacheNames();
        for (String name : cacheNames) {
            Cache cache = cacheManager.getCache(name);
            cache.clear();
        }
        add();
        op_users = userSer.findAll();
        op_users = userSer.findAll();
        op_users = userSer.findAll();
        op_users = userSer.findAll();
        System.out.println(op_users);
    }

    /**
     * 通过用户姓名邮件手机号查找用户
     *
     * @throws SerException
     */
    @Test
    public void verifyByAccountNumber() throws SerException {
        System.out.println(null != userSer.findByAccountNumber("liguiqin"));

    }

    @Test
    public void add() throws SerException {
        List<User> users = new ArrayList<>();
        try {
            for (int i = 0; i < 5; i++) {
                User user = new User();
                user.setUsername("l8hqw_test" + i);
                user.setPassword(PasswordHash.createHash("123456"));
                user.setPhone("1809791024" + i);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        userSer.save(users);

    }

    @Test
    public void cacheUser() throws SerException {
        Optional<User> op_user = userSer.findByPhone("13257910244");
        if (op_user.isPresent()) {
            User user = op_user.get();
            user.setUsername("liguiqin666");
            System.out.println(user);
        }

    }


}
