
import com.alibaba.fastjson.JSON;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.exception.SerException;
import org.junit.Before;
import org.ndshop.goods.dto.GoodsDto;
import org.ndshop.goods.entity.*;
import org.ndshop.goods.enums.GoodsCategory;
import org.ndshop.goods.enums.GoodsElectricType;
import org.ndshop.goods.enums.SaleStatus;
import org.ndshop.goods.service.IGoodsSer;
import org.ndshop.goods.service.IShopsSer;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.service.IUserSer;
import com.dounine.corgi.spring.rpc.Reference;
import goods.provider.test.ApplicationConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
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

    }

    @Test
    public void updateGoodsDes() throws SerException{
        String goodId = "d0ce7082-3338-4cd3-844f-ca8e4cb18896";
        Goods goods = goodsSer.findById( goodId );

        GoodsDes goodsDes = new GoodsDes();
        goodsDes.setDescription("新iphone特性：新增亮面黑，压力感应HOME键，ip67级防水防尘，1200w像素光学防抖，，全新A10处理器，续航提升，立体扬声器 ，lightning耳机接入方式，双摄像头。");
        goodsDes.setSaleStatus(SaleStatus.ONSHELF);
        goodsDes.setModifyTime(LocalDateTime.now());
        goodsDes.setCreateTime( goods.getGoodsDes().getCreateTime() );
        goodsDes.setId( goods.getGoodsDes().getId() );
        goodsDes.setGoods( goods );

        goods.setGoodsDes(goodsDes);
        goodsSer.update( goods );
    }

    @Test
    public void updateGoodsInventorys() throws SerException {
        String goodId = "d0ce7082-3338-4cd3-844f-ca8e4cb18896";
        Goods goods = goodsSer.findById( goodId );

        GoodsInventory goodsInventory =  new GoodsInventory();
        goodsInventory.setQuanty( 100L );
        goodsInventory.setHasSaleQuanty( 12l);
        goodsInventory.setCreateTime( goods.getGoodsInventory().getCreateTime() );
        goodsInventory.setModifyTime( LocalDateTime.now() );
        goodsInventory.setId( goods.getGoodsInventory().getId() );
        goodsInventory.setGoods(  goods );

        goods.setGoodsInventory( goodsInventory );
        goodsSer.update(  goods );

    }

//    @Test
//    public void updateGoods() throws SerException {
//        GoodsDto goodsDto = new GoodsDto();
//        List<Condition> conditions = goodsDto.getConditions();
//        conditions.add(    )
//    }



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


//    @Test
//    public void addforShop() throws  SerException{
//        User user = userSer.findByUsername("liguiqin");
//        Shops shops = new Shops();
//        shops.setUser(user);
//        shops.setShopsName("店铺1号");
//        shopsSer.save(shops);
//    }
//
//    @Test
//    public void findAllShop() throws SerException{
//        List<Shops> shops = shopsSer.findAll();
//        for(Shops shop : shops){
//            System.out.println(JSON.toJSONString(shop));
//        }
//    }


}
