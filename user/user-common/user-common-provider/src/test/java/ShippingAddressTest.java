import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.user.common.entity.ShippingAddress;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.enums.SexType;
import org.ndshop.user.common.service.IShippingAddressSer;
import org.ndshop.user.common.service.IUserSer;
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
    public void addShippingAddress() throws SerException {
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
    public void setDefaultAddress() throws SerException {
        List<ShippingAddress> address = addressSer.findAddressByCurrentUser();
        if (address != null && address.size() > 0) {
            ShippingAddress address1 = address.get(0);
            addressSer.setDefaultAddress(address1);
        }
    }

    @Test
    public void finAll() throws SerException {
        List<ShippingAddress> address = addressSer.findAll();
        ShippingAddress address1 = address.get(0);
        System.out.println(address1);
    }

    public static void main(String[] args) {
        String str = "User";
        String first = String.valueOf(str.charAt(0));
        System.out.println(str.replaceFirst(first , first.toLowerCase()));

    }


}
