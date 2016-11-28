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
import org.ndshop.goods.service.IGoodsSer;
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
        goods.setGoodsNum("商品编号");
        goods.setName("苹果手机");
        goods.setGoodsDescription( "苹果描述");
        goods.setCreateTime( LocalDateTime.now() );
        goods.setModifyTime( LocalDateTime.now() );

        String brandId = "5a7b0fdf-6a96-46de-baed-2d855e7616ed";
        GoodsBrands goodsBrands = new GoodsBrands();
        goodsBrands.setId( brandId );
        goods.setGoodsBrands( goodsBrands );

        String categoryId = "023ffb75-f063-4846-807a-f94a540e9916";
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setId( categoryId );
        goods.setGoodsCategory(  goodsCategory );

        goodsSer.save( goods );
    }

    @Transactional
    @Test
    public void findGoodsById() throws SerException{
        String gid = "2a27c9d2-5536-478d-982e-0645731e4679";
        Goods goods = goodsSer.findById( gid );
        logger.info(JSON.toJSONString( goods ));
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
