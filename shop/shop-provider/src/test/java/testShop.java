import com.dounine.corgi.cluster.Balance;
import com.dounine.corgi.rpc.RpcApp;
import com.dounine.corgi.spring.ApplicationContext;
import com.dounine.corgi.spring.rpc.Reference;
import com.dounine.corgi.spring.rpc.ReferenceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.exception.SerException;


import org.ndshop.shop.entity.Shop;
import org.ndshop.shop.enums.ShopStatus;
import org.ndshop.shop.service.ILogisticsSer;
import org.ndshop.shop.service.IShopSer;
import org.ndshop.testshop.App;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.service.IUserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

/**
 * Created by ike on 16-11-10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class)
public class testShop extends AbstractJUnit4SpringContextTests{

    @Autowired
    private IShopSer shopSer;

/*    @Reference(url = "localhost:8888")*/
    private IUserSer userSer;


    @Before
    public void initContext() throws SerException {

        ApplicationContext.setApplicationContext(super.applicationContext);

        /*User user = userSer.findByUsername("Sarom");
        if(user==null){
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
//        user = userSer.findByUsername("Sarom");     //user save后缓存没有更新

        Shop shop = shopSer.findByName("AAA");
        if (shop==null) {
            shop = new Shop();
            shop.setStatus(ShopStatus.OFFLINE);
            shop.setName("AAA");
            shop.setShopImg("");
            shop.setCreateTime(LocalDateTime.now());
            shop.setLastModiTime(LocalDateTime.now());
            shop.setIntro("");
            shop.setShortIntro("");

            shop.setUser(user);
            shopSer.save(shop);
        }*/
    }

    @Test
    public void testFindShop() throws SerException {

        System.out.println(shopSer.findByName("AAA"));
        System.out.println(shopSer.findByName("AAA"));
        System.out.println(shopSer.findByName("AAA"));
        System.out.println(shopSer.findByName("AAA"));
    }

    @Test
    public void testFindShopByOwner() throws SerException {

        User sarom = new User();
        sarom.setUsername("Sarom");
        Assert.assertTrue(shopSer.findByUser(sarom).size()>0);

        sarom = new User();
//        sarom.setUsername("Sarom");
        sarom.setId("6e87f3da-1d58-429c-995a-5aef34afde33");    //id
        Assert.assertTrue(shopSer.findByUser(sarom).size()>0);
        int size = shopSer.findByUser(sarom).size();
        System.out.printf(String.valueOf(size));
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
        User user = new User();
        user.setUsername("Sarom");
        shopSer.addShopByUser(shop, user);

        Assert.assertTrue(shopSer.findByUser(user).size()>1&&shopSer.findByName("tianmaochaoshi")!=null);
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
    public void testServiceCacheEvict() throws SerException {
        //测试缓存失效,根据AAA仍然查出BBB,则错
        Shop shop = shopSer.findByName("AAA");
        shop.setName("BBB");
        shopSer.update(shop,"AAA");

        Shop shop2 = shopSer.findByName("AAA");
        Assert.assertNull(shop2);

        //测试更新所有者，清除dao缓存
        User user = new User();
        user.setUsername("Sarom");
        shopSer.findByUser(user);
        Set<Shop> set1 = shopSer.findByUser(user);
        Shop shop3 = new Shop();
        shop3.setStatus(ShopStatus.OFFLINE);
        shop3.setName("DDD");
        shop3.setShopImg("");
        shop3.setCreateTime(LocalDateTime.now());
        shop3.setLastModiTime(LocalDateTime.now());
        shop3.setIntro("");
        shop3.setShortIntro("");

        shopSer.addShopByUser(shop3, user);
        shopSer.findByUser(user);
        Set<Shop> set2 = shopSer.findByUser(user);
        Assert.assertTrue(set1.size()<set2.size());
    }
/*    @Test
    public void testDaoCache(){
        shopSer.testDao();
    }*/


}
