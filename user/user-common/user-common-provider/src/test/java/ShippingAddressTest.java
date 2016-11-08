import com.bjike.ndshop.dbs.jpa.dto.Condition;
import com.bjike.ndshop.dbs.jpa.enums.DataType;
import com.bjike.ndshop.dbs.jpa.enums.RestrictionType;
import com.bjike.ndshop.dbs.jpa.exception.SerException;
import com.bjike.ndshop.user.common.dto.ShippingAddressDto;
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

        /*//获取当前用户
        User currentUser = new User();
        //temp
        currentUser.setId("ddf6e579-8091-46fc-910e-9cceb9aec659");
        ShippingAddressDto dto = new ShippingAddressDto();

        Condition condition = new Condition(User.class.getName()+"#id", DataType.STRING,currentUser.getId());
        condition.setRestrict(RestrictionType.LIKE);
        dto.getConditions().add(condition);
        System.out.println(addressSer.countByCis(dto));
        List<ShippingAddress> address = addressSer.findByCis(dto);
        System.out.println( address);
*/

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
