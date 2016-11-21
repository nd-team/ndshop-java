import com.alibaba.fastjson.JSON;
import com.dounine.corgi.spring.rpc.Reference;
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
import org.ndshop.goods.entity.GoodsBrand;
import org.ndshop.goods.entity.GoodsCategory;
import org.ndshop.goods.service.IGoodsBrandSer;
import org.ndshop.goods.service.IGoodsCategorySer;
import org.ndshop.goods.service.IGoodsSer;
import org.ndshop.user.common.service.IUserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by ike on 16-11-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestGoods {
    private static Logger logger = Logger.getLogger(TestGoods.class);
    /**
     * 基础增删改查，批量操作等
     */

    @Autowired
    private IGoodsSer goodsSer;
    @Autowired
    private IGoodsCategorySer goodsCategorySer;
    @Autowired
    private IGoodsBrandSer goodsBrandSer;
    @Reference
    private IUserSer userSer;

    @Test
    public void init() throws SerException {
        if (null == goodsSer.findByGoodName("吹风机")) {
            //temp
            Goods goods = new Goods();
            goods.setGoodsName("吹风机");
            goods.setPrice(5000.0);
            goods.setGoodsLength(13.4);
            goods.setGoodsWidth(2.5);
            goods.setGoodsHeight(10.6);
            goods.setGoodsWeight(10.8);

            goodsSer.save(goods);


        }

    }

    @Test
    public void updateForiegnKey() throws SerException {
        String categoryId = "cdea3266-f283-49d6-bb5c-bf06905e03c2";
        String gid = "b0f42a2a-6c28-42bd-9e5e-969733ae7a84";
        Goods goods = goodsSer.findById(gid);

        GoodsCategory gd = goodsCategorySer.findById(categoryId);
        goods.setGoodsCategory(gd);
        goodsSer.update(goods);

        String goodBrandId = "260a42cf-67cd-4285-a617-d08de8ab3b55";
        GoodsBrand goodsBrand = goodsBrandSer.findById(goodBrandId);
        goods.setGoodsBrand(goodsBrand);
        goodsSer.update(goods);
    }

    @Test
    public void findGoodsByFirstCategory() throws SerException{
        String firstCagetory = "BEAUTI";
        GoodsDto dto = new GoodsDto();

        Condition c = new Condition("name", DataType.STRING , firstCagetory);
        c.setRestrict( RestrictionType.EQ );
        c.fieldToModels( GoodsCategory.class );
        dto.getConditions().add( c );

        List<Goods> goods = goodsSer.findByCis( dto );
        logger.info(JSON.toJSONString( goods ));
    }
}
