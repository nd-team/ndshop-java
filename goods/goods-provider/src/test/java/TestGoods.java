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
import org.ndshop.goods.entity.GoodsThirdCategory;
import org.ndshop.goods.service.IGoodsBrandSer;
import org.ndshop.goods.service.IGoodsSer;
import org.ndshop.goods.service.IGoodsThirdCategorySer;
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
    private IGoodsThirdCategorySer goodsThirdCategorySer;
    @Autowired
    private IGoodsBrandSer goodsBrandSer;
    @Reference
    private IUserSer userSer;

    /**
     * 添加商品
     * @throws SerException
     */
    @Test
    public void init() throws SerException {
        if (null == goodsSer.findByGoodName("食物")) {
            //temp
            Goods goods = new Goods();
            goods.setGoodsName("食物");
            goods.setPrice(5000.0);
            goods.setGoodsLength(13.4);
            goods.setGoodsWidth(2.5);
            goods.setGoodsHeight(10.6);
            goods.setGoodsWeight(10.8);

            goodsSer.save(goods);


        }

    }

    /**
     * 根据三级分类找到商品 ，再更改商品
     * @throws SerException
     */
    @Test
    public void updateForiegnKey() throws SerException {
        String thirdCategoryId = "62cc8b1c-8df6-40cb-a540-701f17c47136";
        String gid = "4a15a242-a907-4c00-bcb1-bea0a29311b5";
        Goods goods = goodsSer.findById(gid);

//        GoodsThirdCategory gd = goodsThirdCategorySer.findById(thirdCategoryId);
//        goods.setGoodsThirdCategory(gd);
//        goodsSer.update(goods);

        String goodBrandId = "aec591c1-7389-4b88-8d3b-91084b5554b9";
        GoodsBrand goodsBrand = goodsBrandSer.findById(goodBrandId);
        goods.setGoodsBrand(goodsBrand);
        goodsSer.update(goods);
    }

    /**
     * 根据三级分类找到商品
     * @throws SerException
     */
    @Test
    public void findGoodsByThirdCategory() throws SerException{
        String thirdCagetory = "BEAUTI";
        GoodsDto dto = new GoodsDto();

        Condition c = new Condition("thirdName", DataType.STRING , thirdCagetory);
        c.setRestrict( RestrictionType.EQ );
        c.fieldToModels( GoodsThirdCategory.class );
        dto.getConditions().add( c );

        List<Goods> goods = goodsSer.findByCis( dto );
        logger.info(JSON.toJSONString( goods ));
    }
}
