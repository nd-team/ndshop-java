
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.dto.GoodsCategoryDto;
import org.ndshop.goods.dto.GoodsCollectionDto;
import org.ndshop.goods.dto.GoodsPicDto;
import org.ndshop.goods.entity.*;
import org.ndshop.goods.enums.BrandStatus;
import org.ndshop.goods.enums.CollectionStatus;
import org.ndshop.goods.enums.SaleStatus;
import org.ndshop.goods.service.*;
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
import java.time.format.DateTimeFormatter;
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
        String categoryId = "7b971dda-fd95-4f15-853c-d454d2ae4757";
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

            GoodsCategory gd = goodsCategorySer.findById( categoryId );
            goods.setGoodsCategory( gd );
            goodsSer.update( goods );

            String goodBrandId = "54731781-0a9d-4c30-98f9-69f1f9b6d7cf";
            if( goodBrandId != null ){
                GoodsBrand goodsBrand = goodsBrandSer.findById( goodBrandId );
                goods.setGoodsBrand( goodsBrand );
                goodsSer.update( goods );
            }


            String shopId = "111111";
            GoodsShops goodsShops = new GoodsShops();
            goodsShops.setGoods(goods);
            Shops shops = new Shops();
            shops.setId( shopId );
            goodsShops.setShops( shops );
            Set<GoodsShops> gs = new HashSet<>();
            gs.add(goodsShops);
            goods.setGoodsShops(gs);
            goodsSer.update( goods );


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
//        User user =userSer.findByUsername("liguiqin");
        List<Goods> goods = goodsSer.findAll();
        for(Goods g : goods){
            logger.info( JSON.toJSONString(g));
        }
    }

    /**
     * 添加分类
     * @throws SerException
     */

    @Test
    public void addCategory() throws SerException{
        String categoryName = "BEAUTI";
        String secondName = "FACEBEAUTI";
        String thirdName = "HAIR CARE";
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setName(  categoryName );
        goodsCategory.setSecondName( secondName );
        goodsCategory.setThirdName( thirdName );
        goodsCategory.setCreateTime( LocalDateTime.now() );
        goodsCategory.setModifyTime(LocalDateTime.now() );
        goodsCategorySer.save(  goodsCategory );
    }


    @Test
    public void updateCategory() throws SerException{
        String cateoryId ="88fe1f03-1e6b-44e5-a09f-ebbb880d5cce";
        String categoryName = "BEAUTI";
        String secondName = "FACEBEAUTI";
        String thirdName = "HAIR CARE";

        GoodsCategory goodsCategory = goodsCategorySer.findById( cateoryId );
        if ( categoryName != "" &&  categoryName != null) {
            goodsCategory.setSecondName( categoryName );
        }
        if ( secondName !="" && secondName != null ) {
            goodsCategory.setSecondName( secondName );
        }
        if ( thirdName != "" && thirdName != null ) {
            goodsCategory.setThirdName( thirdName );
        }
        goodsCategory.setModifyTime( LocalDateTime.now() );
        goodsCategory.setId( cateoryId );
        goodsCategorySer.update( goodsCategory );
        logger.info( JSON.toJSONString( goodsCategory ) );
    }


    @Test
    public void findCategoryByFirstCategory () throws  SerException{
        String name = "ELECTRC";
        Condition condition = new Condition("name",DataType.STRING ,name);
        GoodsCategoryDto dto = new GoodsCategoryDto();
        dto.getConditions().add( condition );
        dto.setLimit(2);
        dto.setPage(1);
        List<GoodsCategory> goodCategory = goodsCategorySer.findByCis( dto,true );
        logger.info(JSON.toJSONString(goodCategory) );
    }


    /**
     * 测试商品图片
     */
    @Test
    public  void addGoodsPic () throws  SerException{
        String gid ="0ad8ccdd-6ccf-40e1-8647-f6fadb4973b8";
        Goods goods = goodsSer.findById( gid );
        GoodsPic goodsPic = new GoodsPic();
        String time = goodsPic.getCreateTime().minusSeconds(0).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        goodsPic.setPicUrl("/home/xx/xx"+time+".jpg");
        goodsPic.setFlag("详情图片");
        goodsPic.setGoods( goods );
        goodsPicSer.save(  goodsPic );
        logger.info(time+""+ goodsPic );

    }

    @Test
    public  void updateGoodsPic () throws  SerException{
        String picId ="cc8a3190-83c1-4a52-b61b-9b22f34e08b0";
        GoodsPic goodsPic = new GoodsPic();
        String time = goodsPic.getCreateTime().minusSeconds(0).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        goodsPic.setPicUrl("/home/xx/xx"+time+".jpg");
        goodsPic.setFlag("详情图片");
        goodsPicSer.update(  goodsPic );
        logger.info(time+""+ goodsPic );

    }

    /**
     * 根据商品id查找商品图片
     * @throws SerException
     */
    @Test
    public void findGoodsPic() throws  SerException{
        String gid ="805968df-78cf-416b-b0ff-030638599584";
        Condition condition = new Condition("id", DataType.STRING , gid);
        condition.fieldToModels(Goods.class);
        GoodsPicDto goodsPicDto = new GoodsPicDto();
        condition.setRestrict(RestrictionType.EQ);
        goodsPicDto.getConditions().add( condition );
        List<GoodsPic> gp = goodsPicSer.findByCis( goodsPicDto );
        logger.info( JSON.toJSONString( gp ));
    }

    /**
     * 添加商品 品牌
     */
    @Test
    public void addBrand() throws SerException{
        String brandName = "喜羊羊";
        GoodsBrand goodsBrand = new GoodsBrand();
        goodsBrand.setBrandName(  brandName );
        goodsBrand.setBrandStatus(BrandStatus.FROZEN.getName() );
        goodsBrand.setCreateTime(  LocalDateTime.now() );
        goodsBrand.setModifyTime(  LocalDateTime.now() );
        goodsBrandSer.save( goodsBrand );
    }

    @Test
    public void updateBrand ()throws SerException{
        String name = "美羊羊";
        String bid = "4069cb12-bbd0-4641-aa93-de4c811fe2b0";
        GoodsBrand goodsBrand = goodsBrandSer.findById( bid );
        if (goodsBrand != null ){
            goodsBrand.setBrandName( name );
            goodsBrand.setModifyTime( LocalDateTime.now() );
            goodsBrandSer.update( goodsBrand );
        }
        logger.info ( JSON.toJSONString( goodsBrand ) );
    }

    @Test
    public void updateBrandstatus ()throws SerException{
        String status = BrandStatus.ACTIVE.getName();
        String bid = "4069cb12-bbd0-4641-aa93-de4c811fe2b0";
        GoodsBrand goodsBrand = goodsBrandSer.findById( bid );
        if (goodsBrand != null ){
            goodsBrand.setBrandStatus( status );
            goodsBrand.setModifyTime( LocalDateTime.now() );
            goodsBrandSer.update( goodsBrand );
        }
        logger.info( JSON.toJSONString ( goodsBrand ) );
    }

    @Test
    public void findBrand() throws SerException{
        List<GoodsBrand> goodsBrands = goodsBrandSer.findAll();
        logger.info( JSON.toJSONString( goodsBrands));
    }

    @Test
    public void addCollection() throws SerException{
        String gid = "0ad8ccdd-6ccf-40e1-8647-f6fadb4973b8";
        String userid = "drge345456reyrh";
        GoodsCollection goodsCollection = new GoodsCollection();
        Goods goods = goodsSer.findById(  gid );
        goodsCollection.setGoods( goods  );

        User user = userSer.findById( userid );
        System.out.println(user);
        goodsCollection.setUser( user );

        GoodsCollectionDto dto = new GoodsCollectionDto();
        Condition c1 = new Condition("id",DataType.STRING,gid);
        c1.fieldToModels(Goods.class);
        c1.setRestrict( RestrictionType.EQ );

        Condition c2 = new Condition("id",DataType.STRING,gid);
        c2.fieldToModels(Goods.class);
        c2.setRestrict( RestrictionType.EQ );

        dto.getConditions().add( c1 );
        dto.getConditions().add( c2 );


        List<GoodsCollection> list = goodsCollectionSer.findByCis( dto );
        if( list !=null && list.size() > 0 ){
            goodsCollection.setStatus( CollectionStatus.NONFOUCING.name() );
            goodsCollection.setCreateTime( list.get(0).getCreateTime() );
            goodsCollection.setModifyTime(  LocalDateTime.now() );
            goodsCollection.setId(  list.get(0).getId() );

            goodsCollectionSer.update( goodsCollection );
        }else{
            goodsCollection.setStatus( CollectionStatus.FOUCING.name() );
            goodsCollection.setCreateTime( LocalDateTime.now() );
            goodsCollection.setModifyTime(  LocalDateTime.now() );

            goodsCollectionSer.save( goodsCollection );
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
