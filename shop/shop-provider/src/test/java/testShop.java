import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.exception.SerException;


import org.ndshop.shop.entity.Shop;
import org.ndshop.shop.enums.ShopStatus;
import org.ndshop.shop.service.IShopSer;
import org.ndshop.testshop.App;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.service.IUserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

/**
 * Created by ike on 16-11-10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class)
public class testShop {

    @Autowired
    private IShopSer shopSer;

    @Autowired
    private IUserSer userSer;

    @Before
    public void init() throws SerException {
        if (shopSer.findByName("AAA") == null) {
            Shop shop = new Shop();
            shop.setStatus(ShopStatus.OFFLINE);
            shop.setName("AAA");
            shop.setShopImg("");
            shop.setCreateTime(LocalDateTime.now());
            shop.setLastModiTime(LocalDateTime.now());
            shop.setIntro("");
            shop.setShortIntro("");

            User user = userSer.findByUsername("Sarom");
            if (user == null) {
                //创建用户
                user = new User();
                user.setUsername("Sarom");
                user.setCreateTime(LocalDateTime.now());
                user.setAccessTime(LocalDateTime.now());
                user.setAge(22);
                user.setEmail("saromc@qq.com");
                user.setPassword("123456");
                user.setPhone("188888888");
                userSer.save(user);
            }
            shop.setOwner(user);

            shopSer.save(shop);
        }
    }

    @Test
    public void testFindShop() {
        System.out.println(shopSer.findByName("AAA"));
    }

    @Test
    public void testFindShopByOwner() {
        System.out.println(shopSer.findByOwnerName("Sarom"));
        System.out.println(shopSer.findByOwnerName("Sarom"));
    }

    @Test
    public void testAddShop() throws SerException {
        //已有用户添加shop，测试
        Shop shop = new Shop();
        shop.setStatus(ShopStatus.OFFLINE);
        shop.setName("tianmaochaoshi");
        shop.setShopImg("");
        shop.setCreateTime(LocalDateTime.now());
        shop.setIntro("");
        shop.setShortIntro("");

        //添加到已有商铺的商家Sarom
        shopSer.addShopByOwnerName(shop, "Sarom");
    }


    @Test
    public void testShopStatusChange() throws SerException {
        //测试
        Shop shop = shopSer.findByName("AAA");
        if (shop!=null){
            shopSer.shopStatusChange(shop);
        }
    }

    @Test
    public void testChange1() throws SerException {
        //测试service缓存，调用多个现有Service
        shopSer.shopStatusChange("AAA");
        shopSer.findByName("AAA");
        shopSer.findByName("AAA");
    }

    @Test
    public void testShopStatusChange2(){
        //使用jpql直接update
        shopSer.shopStatusChange("AAA",1);
    }

    @Test
    public void testDaoCache() throws SerException {
        //测试dao缓存,根据AAA仍然查出BBB,则错
        Shop shop = shopSer.findByName("AAA");
        shop.setName("BBB");
        shopSer.update(shop);
        Shop shop2 = shopSer.findByName("AAA");
        System.out.println(shop2.getName());
    }
}
