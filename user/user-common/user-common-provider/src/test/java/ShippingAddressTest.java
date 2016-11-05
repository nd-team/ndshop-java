import com.bjike.ndshop.dbs.jpa.exception.SerException;
import com.bjike.ndshop.user.common.entity.ShippingAddress;
import com.bjike.ndshop.user.common.entity.User;
import com.bjike.ndshop.user.common.enums.SexType;
import com.bjike.ndshop.user.common.service.IShippingAddressSer;
import com.bjike.ndshop.user.common.service.IUserSer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import user_common_code.ApplicationConfiguration;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ShippingAddressTest {


    @Autowired
    private IShippingAddressSer addressSer;
    @Autowired
    private IUserSer userSer;

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


    @Test
    public void addShippingAddress()throws  SerException{
        ShippingAddress address = new ShippingAddress();
        address.setPhone("13457910241");
        address.setEmail("aml@qq.com");
        address.setArea("兰州");
        address.setDetailAddress("兰州兰州");
        address.setUser(userSer.findByUsername("liguiqin"));
        address.setReceiverName("小白");
        addressSer.addShippingAddress(address);
    }

    @Test
    public void setDefaultAddress()throws  SerException{
        List<ShippingAddress> address = addressSer.findAddressByCurrentUser();
        ShippingAddress address1 =  address.get(0);
        addressSer.setDefaultAddress(address1);
    }



}
