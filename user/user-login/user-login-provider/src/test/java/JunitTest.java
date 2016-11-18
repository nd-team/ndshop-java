import org.ndshop.dbs.jpa.exception.SerException;
import org.junit.Test;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.login.service.IUserLoginSer;
import com.dounine.corgi.spring.ApplicationContext;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import user_login_code.ApplicationConfiguration;

/**
 * Created by huanghuanlai on 2016/10/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class JunitTest extends AbstractJUnit4SpringContextTests {

    /**
     * 基础增删改查，批量操作等
     */

    @Autowired
    private IUserLoginSer userLoginSer;


    @Before
    public void initContext() {
        ApplicationContext.setApplicationContext(super.applicationContext);
    }

    @Test
    public void loginUser()throws SerException{
        userLoginSer.login(new User());
    }


}
