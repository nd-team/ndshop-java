
import com.alibaba.fastjson.JSON;
import com.bjike.ndshop.dbs.jpa.exception.SerException;
import org.junit.Before;
import org.ndshop.goods.entity.*;
import org.ndshop.goods.enums.GoodsCategory;
import org.ndshop.goods.enums.GoodsElectricType;
import org.ndshop.goods.service.IGoodsSer;
import org.ndshop.goods.service.IShopsSer;
import com.bjike.ndshop.user.common.entity.User;
import com.bjike.ndshop.user.common.service.IUserSer;
import com.dounine.corgi.spring.rpc.Reference;
import goods.provider.test.ApplicationConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ike on 2016/11/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class JunitTest {

    /**
     * 基础增删改查，批量操作等
     */

    @Autowired
    private IGoodsSer goodsSer;
    @Autowired
    private IShopsSer shopsSer;
    @Reference
    private IUserSer userSer;

    @Before
    public  void init () throws SerException {
        if(null == goodsSer.findByGoodName("小苹果手机")  ){
            //temp
            Goods goods = new Goods();
            goods.setGoodsName("小苹果手机");
            goods.setPrice(2000.0);
            goods.setGoodsLength(3.4);
            goods.setGoodsWidth(1.5);
            goods.setGoodsHeight(5.6);
            goods.setGoodsWeight(0.8);
            goods.setFirstCategory(GoodsCategory.ELECTRC.toString() );
            goods.setSecondCategory(GoodsElectricType.BIGELECTRIC.toString());

            GoodsDes goodsDes = new GoodsDes();
            goodsDes.setGoods(goods);
            goods.setGoodsDes(goodsDes);

            GoodsInventory goodsInventory = new GoodsInventory();
            goodsInventory.setGoods(goods);
            goods.setGoodsInventory(goodsInventory);

            GoodsShops goodsShops = new GoodsShops();
            goodsShops.setGoods(goods);
            Set<GoodsShops> gs = new HashSet<>();
            gs.add(goodsShops);
            goods.setGoodsShops(gs);

            goodsSer.save(goods);



        }
        else{
            Goods gds = goodsSer.findByGoodName("小苹果手机");
            gds.setFirstCategory(GoodsCategory.ELECTRC.toString() );
            gds.setSecondCategory(GoodsElectricType.BIGELECTRIC.toString());
            goodsSer.update(gds);
        }

    }

    /**
     * 查询全部
     */
    @Test
    public void findAll() throws SerException {
//        User user =userSer.findByUsername("liguiqin");
        List<Goods> goods = goodsSer.findAll();
        for(Goods g : goods){
            System.out.println(JSON.toJSONString(g));
        }
    }


    @Test
    public void addforShop() throws  SerException{
        User user = userSer.findByUsername("liguiqin");
        Shops shops = new Shops();
        shops.setUser(user);
        shops.setShopsName("店铺1号");
        shopsSer.save(shops);
    }

    @Test
    public void findAllShop() throws SerException{
        List<Shops> shops = shopsSer.findAll();
        for(Shops shop : shops){
            System.out.println(JSON.toJSONString(shop));
        }
    }


}
