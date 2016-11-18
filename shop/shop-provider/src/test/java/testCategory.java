import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.shop.entity.Category;
import org.ndshop.shop.entity.Shop;
import org.ndshop.shop.enums.ShopStatus;
import org.ndshop.shop.service.ICategorySer;
import org.ndshop.shop.service.IShopSer;
import org.ndshop.testshop.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

/**
 * Created by ike on 16-11-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = App.class)
public class testCategory {


    @Autowired
    private ICategorySer categotySer;

    @Autowired
    private IShopSer shopSer;

    @Before
    public void init() throws SerException {

        if(categotySer.findByName("cate1")==null){
            //创建店内分类
            Category category = new Category();
            category.setName("cate1");
            category.setStatus(ShopStatus.OFFLINE);
            category.setCreateTime(LocalDateTime.now());

            //关联已有店铺
            Shop shop = shopSer.findByName("AAA");
            category.setShop(shop);

            categotySer.save(category);
        }

    }


    @Test
    public void testFindByName(){
        System.out.println(categotySer.findByName("cate1"));
    }


}
