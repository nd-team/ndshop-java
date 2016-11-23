import com.dounine.corgi.spring.rpc.Reference;
import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.entity.*;
import org.ndshop.goods.enums.SaleStatus;
import org.ndshop.goods.service.*;
import org.ndshop.user.common.service.IUserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

/**
 * Created by ike on 2016/11/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class JunitTest {
    private static Logger logger = Logger.getLogger(JunitTest.class);
    /**
     * 基础增删改查，批量操作等
     */

    @Autowired
    private IGoodsSer goodsSer;
    @Autowired
    private IGoodsThirdCategorySer goodsThirdCategorySer;
    @Autowired
    private IGoodsCategorySer goodsCategorySer;
    @Autowired
    private IGoodsPicSer goodsPicSer;
    @Autowired
    private IGoodsBrandSer goodsBrandSer;
    @Autowired
    private IGoodsCollectionSer goodsCollectionSer;
    @Autowired
    private IShopsSer shopsSer;
    @Reference
    private IUserSer userSer;

    @Test
    public  void init () throws SerException {
        String thirdCategoryId = "7b971dda-fd95-4f15-853c-d454d2ae4757";
        if(null == goodsSer.findByGoodName("洗衣机")  ){
            //temp
            Goods goods = new Goods();
            goods.setGoodsName("洗衣机");
            goods.setPrice(5000.0);
            goods.setGoodsLength(13.4);
            goods.setGoodsWidth(2.5);
            goods.setGoodsHeight(10.6);
            goods.setGoodsWeight(10.8);


            GoodsDes goodsDes = new GoodsDes();
            goodsDes.setSaleStatus( SaleStatus.ONSHELF );
            goodsDes.setGoods(goods);
            goods.setGoodsDes(goodsDes);

            GoodsInventory goodsInventory = new GoodsInventory();
            goodsInventory.setQuanty( 110l );
            goodsInventory.setGoods(goods);
            goods.setGoodsInventory(goodsInventory);

            goodsSer.save(goods);

//            GoodsThirdCategory gd = goodsThirdCategorySer.findById( thirdCategoryId );
//            goods.setGoodsThirdCategory( gd );
//            goodsSer.update( goods );
//
            String goodBrandId = "54731781-0a9d-4c30-98f9-69f1f9b6d7cf";
            if( goodBrandId != null ){
                GoodsBrand goodsBrand = goodsBrandSer.findById( goodBrandId );
                goods.setGoodsBrand( goodsBrand );
                goodsSer.update( goods );
            }


//            String shopId = "111111";
//            GoodsShops goodsShops = new GoodsShops();
//            goodsShops.setGoods(goods);
//            Shops shops = new Shops();
//            shops.setId( shopId );
//            goodsShops.setShops( shops );
//            Set<GoodsShops> gs = new HashSet<>();
//            gs.add(goodsShops);
//            goods.setGoodsShops(gs);
//            goodsSer.update( goods );


        }

    }

    @Test
    public void updateGoodsDes() throws SerException{
        String goodId = "805968df-78cf-416b-b0ff-030638599584";
        Goods goods = goodsSer.findById( goodId );

        GoodsDes goodsDes = new GoodsDes();
        goodsDes.setDescription("乐视超4 X50 乐视TV X3-50 UHD液晶平板电视机50英寸3D 4K超3 48");
        goodsDes.setSaleStatus(SaleStatus.ONSHELF);
        goodsDes.setModifyTime(LocalDateTime.now());
        if( goods.getGoodsDes()!=null){
            goodsDes.setCreateTime( goods.getGoodsDes().getCreateTime() );
            goodsDes.setId( goods.getGoodsDes().getId() );
        }else{
            goodsDes.setCreateTime( LocalDateTime.now() );
            goodsDes.setId( goodId );
        }

        goodsDes.setGoods( goods );

        goods.setGoodsDes(goodsDes);
        goodsSer.update( goods );
    }

    @Test
    public void updateGoodsInventorys() throws SerException {
        String goodId = "805968df-78cf-416b-b0ff-030638599584";
        Goods goods = goodsSer.findById( goodId );

        GoodsInventory goodsInventory =  new GoodsInventory();
        goodsInventory.setQuanty( 100L );
        goodsInventory.setHasSaleQuanty( 12l);
        if( goods.getGoodsDes()!=null){
            goodsInventory.setCreateTime( goods.getGoodsInventory().getCreateTime() );
            goodsInventory.setId( goods.getGoodsInventory().getId() );
        }else{
            goodsInventory.setCreateTime( LocalDateTime.now() );
            goodsInventory.setId( goodId );
        }
        goodsInventory.setModifyTime( LocalDateTime.now() );
        goodsInventory.setGoods(  goods );

        goods.setGoodsInventory( goodsInventory );
        goodsSer.update(  goods );

    }

    /**
     * 查询全部
     */
    @Test
    public void findAll() throws SerException {
        goodsCategorySer.findCategoryByFirstCategory("");
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
