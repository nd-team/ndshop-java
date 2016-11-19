import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.user.register.dto.UserRegisterDto;
import org.ndshop.user.register.service.IUserRegisterSer;
import com.dounine.corgi.spring.ApplicationContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import user_register_code.ApplicationConfiguration;

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
    private IUserRegisterSer userRegisterSer;


    @Before
    public void initContext() {
        ApplicationContext.setApplicationContext(super.applicationContext);
    }


    @Test
    public void existUsername() throws SerException {
        boolean users = userRegisterSer.existUsername("liguiqin");
        System.out.println(users);
    }

    @Test
    public void existPhone() throws SerException {
        boolean users = userRegisterSer.existPhone("13457910241");
         users = userRegisterSer.existPhone("13457910241");
         users = userRegisterSer.existPhone("13457910241");
         users = userRegisterSer.existPhone("13457910241");
         users = userRegisterSer.existPhone("13457910241");
        System.out.println(users);
    }

    @Test
    public void sendCodeToPhone() throws SerException {
        UserRegisterDto dto = new UserRegisterDto();
        dto.setPhone("123456");
        userRegisterSer.sendCodeToPhone(dto);
    }

    @Test
    public void verifyCodeAndReg() throws SerException {
        UserRegisterDto dto = new UserRegisterDto();
        dto.setPhone_code("123");
        userRegisterSer.verifyCodeAndReg(dto);
    }




}
