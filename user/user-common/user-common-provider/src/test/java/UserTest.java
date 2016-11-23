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
 * Created by huanghuanlai on 2016/10/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class UserTest {

    @Autowired
    private IUserSer userSer;
    @Autowired
    CacheManager cacheManager;


    /**
     * 查询全部
     */
    @Test
    public void findAll() throws SerException {
        List<User> users = userSer.findAll();
        users = userSer.findAll();
        users = userSer.findAll();

        Collection<String> cacheNames = cacheManager.getCacheNames();
        for (String name : cacheNames) {
            Cache cache = cacheManager.getCache(name);
            cache.clear();
        }
        add();
        users = userSer.findAll();
        users = userSer.findAll();
        users = userSer.findAll();
        users = userSer.findAll();
        System.out.println(users);
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
        Optional<User> optional = userSer.findByPhone("13257910244");
        if (optional.isPresent()) {
            User user = optional.get();
            user.setUsername("liguiqin666");
            System.out.println(user);
        }

    }


}
