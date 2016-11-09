
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.junit.Before;
import org.ndshop.goods.dto.GoodsCategoryDto;
import org.ndshop.goods.entity.*;
import org.ndshop.goods.enums.GoodsType;
import org.ndshop.goods.enums.GoodsElectricType;
import org.ndshop.goods.enums.SaleStatus;
import org.ndshop.goods.service.GoodsCategoryImpl;
import org.ndshop.goods.service.IGoodsCategorySer;
import org.ndshop.goods.service.IGoodsSer;
import org.ndshop.goods.service.IShopsSer;
import org.ndshop.user.common.service.IUserSer;
import com.dounine.corgi.spring.rpc.Reference;
import goods.provider.test.ApplicationConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.*;

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
    private IGoodsCategorySer goodsCategorySer;
    @Autowired
    private IShopsSer shopsSer;
    @Reference
    private IUserSer userSer;

    @Before
    public  void init () throws SerException {
        if(null == goodsSer.findByGoodName("3D电视机")  ){
            //temp
            Goods goods = new Goods();
            goods.setGoodsName("3D电视机");
            goods.setPrice(5000.0);
            goods.setGoodsLength(13.4);
            goods.setGoodsWidth(2.5);
            goods.setGoodsHeight(10.6);
            goods.setGoodsWeight(10.8);
            goods.setFirstCategory(GoodsType.ELECTRC.toString() );
            goods.setSecondCategory(GoodsElectricType.AUDIOELECTRIC.toString());

            GoodsDes goodsDes = new GoodsDes();
            goodsDes.setGoods(goods);
            goods.setGoodsDes(goodsDes);

            GoodsInventory goodsInventory = new GoodsInventory();
            goodsInventory.setGoods(goods);
            goods.setGoodsInventory(goodsInventory);

            String shopId = "111111";
            GoodsShops goodsShops = new GoodsShops();
            goodsShops.setGoods(goods);
            Shops shops = new Shops();
            shops.setId( shopId );
            goodsShops.setShops( shops );
            Set<GoodsShops> gs = new HashSet<>();
            gs.add(goodsShops);
            goods.setGoodsShops(gs);

            goodsSer.save(goods);



        }

    }

    @Test
    public void updateGoodsDes() throws SerException{
        String goodId = "2cefbead-ed29-4b01-a748-4f4e3443de48";
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
        String goodId = "d0ce7082-3338-4cd3-844f-ca8e4cb18896";
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
//        User user =userSer.findByUsername("liguiqin");
        List<Goods> goods = goodsSer.findAll();
        for(Goods g : goods){
            logger.info( JSON.toJSONString(g));
        }
    }

    @Test
    public void addBatchCategory() throws  SerException {
        List<String> electricType = Arrays.asList("BIGELECTRIC","AUDIOELECTRIC","LIVINGELECTRIC","KITCHENELECTRIC","HEALTHELECTRIC");
        String categoryName = "ELECTRC";
        List<GoodsCategory> goodElectric = new ArrayList<>();
        for(int i =0 ;i<electricType.size() ;i++){
            GoodsCategoryDto  dto = new GoodsCategoryDto();
            Condition condition1 = new Condition("name", DataType.STRING,categoryName);
            Condition condition2 = new Condition("secondName", DataType.STRING,electricType.get(i) );
            dto.getConditions().add(  condition1  );
            dto.getConditions().add(  condition2  );
            GoodsCategory gc = goodsCategorySer.findOne(  dto );
            if( gc == null ){
                GoodsCategory goodsCategory = new GoodsCategory();
                goodsCategory.setName(  categoryName  );
                goodsCategory.setSecondName(  electricType.get(i) );
                goodsCategory.setCreateTime( LocalDateTime.now() );
                goodsCategory.setModifyTime( LocalDateTime.now() );

                goodElectric.add( goodsCategory );
            }
        }
        if( goodElectric != null && goodElectric.size()>0){
            goodsCategorySer.save(  goodElectric );
            System.out.println( JSON.toJSONString( goodElectric ));
        }


    }

    @Test
    public void upateBatchCategory() throws  SerException {
        List<String> electricType = Arrays.asList("BIGELECTRIC","AUDIOELECTRIC","LIVINGELECTRIC","KITCHENELECTRIC","HEALTHELECTRIC");
        String categoryName = "ELECTRC";
        List<GoodsCategory> goodElectricUpdate = new ArrayList<>();
        for(int i =0 ;i<electricType.size() ;i++){
            GoodsCategoryDto  dto = new GoodsCategoryDto();
            Condition condition1 = new Condition("name", DataType.STRING,categoryName);
            Condition condition2 = new Condition("secondName", DataType.STRING,electricType.get(i) );
            dto.getConditions().add(  condition1  );
            dto.getConditions().add(  condition2  );
            GoodsCategory gc = goodsCategorySer.findOne(  dto );
            if( gc!= null ){
                GoodsCategory updateCategory = new GoodsCategory();
                updateCategory.setId( gc.getId() );
                updateCategory.setName(  categoryName  );
                updateCategory.setSecondName(  electricType.get(i) );
                updateCategory.setCreateTime( gc.getCreateTime() );
                updateCategory.setModifyTime( LocalDateTime.now() );
                goodElectricUpdate.add( updateCategory );
            }

        }
        if( goodElectricUpdate != null && goodElectricUpdate.size()>0 ){
            goodsCategorySer.update(  goodElectricUpdate );
            System.out.println( JSON.toJSONString( goodElectricUpdate ));
        }
    }

    @Test
    public void updateCategory() throws SerException{
        String cateoryId ="0dc5aeaa-6cc1-4a01-b0c5-96cf62115653";
        GoodsCategory goodsCategory = goodsCategorySer.findById( cateoryId );
        goodsCategory.setSecondName("HEALTHELECTRIC");
        goodsCategory.setModifyTime( LocalDateTime.now() );
        goodsCategory.setId( cateoryId );
        goodsCategorySer.update( goodsCategory );
        logger.info( JSON.toJSONString( goodsCategory ) );
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
