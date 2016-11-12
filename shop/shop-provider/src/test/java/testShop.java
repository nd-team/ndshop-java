import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.exception.SerException;


import org.ndshop.shop.entity.Shop;
import org.ndshop.shop.enums.ShopStatus;
import org.ndshop.shop.service.IShopSer;
import org.ndshop.testshop.ApplicationConfiguration;
import org.ndshop.user.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

/**
 * Created by ike on 16-11-10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class testShop {

    @Autowired
    private IShopSer shopSer;

    @Before
    public void init() throws SerException {
        if(shopSer.findByName("AAA")==null){
            Shop shop = new Shop();
            shop.setId("32423424242432424");
            shop.setStatus(ShopStatus.OFFLINE);
            shop.setName("AAA");
            shop.setShopImg("");
            shop.setCreateTime(LocalDateTime.now());
            shop.setLastModiTime(LocalDateTime.now());
            shop.setIntro("");
            shop.setShortIntro("");

/*            User user = new User();
            user.setUsername("Sarom");
            shop.setOwner(user);*/


            shopSer.save(shop);
        }
    }

    @Test
    public void testFindShop(){
        shopSer.findByName("AAA");
    }

}
