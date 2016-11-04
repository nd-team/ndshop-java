import com.bjike.ndshop.user.common.service.IUserSer;
import user_register_code.ApplicationConfiguration;
import com.bjike.ndshop.dbs.jpa.exception.SerException;
import com.bjike.ndshop.user.common.entity.User;
import com.bjike.ndshop.user.register.service.IUserRegisterSer;
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
public class JunitTest {

    /**
     * 基础增删改查，批量操作等
     */

    @Autowired
    private IUserRegisterSer userRegisterSer;


    @Test
    public void findAll() throws SerException {
        boolean users = userRegisterSer.existUsername("11");
        System.out.println(users);

    }

}
