import com.alibaba.fastjson.JSON;
import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.dto.GoodsDto;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsBrands;
import org.ndshop.goods.entity.GoodsCategory;
import org.ndshop.goods.enums.*;
import org.ndshop.goods.service.IGoodsSer;
import org.ndshop.user.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-28 10:34]
 * @Description: [商品图片业务测试]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestGoods {
    private static Logger logger = Logger.getLogger(TestGoods.class);

    @Autowired
    private IGoodsSer goodsSer;
    

    @Test
    public void addGoods() throws SerException{
        Goods goods = new Goods();
        goods.setGoodsNum("商品编号2");
        goods.setName("苹果手机2");
        goods.setGoodsDescription( "苹果描述");
        goods.setGoodsCode("4546546565");
        goods.setPrice(199.99);
        goods.setDiscountPrice(188.0);
        goods.setQuantity(1343);
        goods.setGoodsOnSaleStatus(GoodsOnSaleStatus.ONSALE );
        goods.setGoodsSpecialSaleStatus(GoodsSpecialSaleStatus.NORMALSALE );
        goods.setGoodsNewFlagStatus(GoodsNewFlagStatus.isOld );
        goods.setGoodsHotSaleStatus(GoodsHotSaleStatus.NORMALSALE );
        goods.setGoodsRecommendStatus(GoodsRecommendStatus.isnormal );
        if ( GoodsOnSaleStatus.ONSALE.equals( goods.getGoodsOnSaleStatus() ) ) {
            goods.setOnSaleCreateTime( LocalDateTime.now() );
        }else{
            goods.setOnSaleCreateTime( null );
        }
        goods.setCreateTime( LocalDateTime.now() );
        goods.setModifyTime( LocalDateTime.now() );

        String brandId = "5b7a637a-6b21-40eb-981f-0e47c06ad226";
        GoodsBrands goodsBrands = new GoodsBrands();
        goodsBrands.setId( brandId );
        goods.setGoodsBrands( goodsBrands );

        String categoryId = "3ba91a9a-d763-4b16-a3fc-8942510ccb71";
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setId( categoryId );
        goods.setGoodsCategory(  goodsCategory );

        String userId = "40d2f51f-b1ac-48ce-85d9-b4a552a59d60";
        User user = new User();
        user.setId( userId );
        goods.setUser( user );

        goodsSer.save( goods );
    }

    @Transactional
    @Test
    public void findGoodsById() throws SerException{
        String gid = "e329a7cc-d318-4918-bdd1-b57620986144";
        Goods goods = goodsSer.findById( gid );
        logger.info(JSON.toJSONString( goods.getGoodsFieldValue() ));
    }

    @Transactional
    @Test
    public void findByBrandName()throws SerException{
        Condition c = new Condition("name", DataType.STRING ,"36");
        c.fieldToModels( GoodsBrands.class);
        c.setRestrict(RestrictionType.LIKE);

        GoodsDto dto = new GoodsDto();
        dto.getConditions().add( c );
        List<Goods> goods = goodsSer.findByCis( dto );
        logger.info(JSON.toJSONString( goods ) );

//        logger.info( JSON.toJSONString( goodsSer.findByBrandName("36") ));
    }

}
