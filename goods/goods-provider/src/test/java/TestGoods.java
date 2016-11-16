import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.entity.*;
import org.ndshop.goods.service.IGoodsBrandSer;
import org.ndshop.goods.service.IGoodsCategorySer;
import org.ndshop.goods.service.IGoodsSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void init() throws SerException {
        if (null == goodsSer.findByGoodName("洗衣机")) {
            //temp
            Goods goods = new Goods();
            goods.setGoodsName("洗衣机");
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
        String categoryId = "21df8399-4119-4af9-afe3-96a1bf9d87cc";
        String gid = "81861f34-9af6-4858-96b4-4b7c7c67b587";
        Goods goods = goodsSer.findById(gid);

        GoodsCategory gd = goodsCategorySer.findById(categoryId);
        goods.setGoodsCategory(gd);
        goodsSer.update(goods);

        String goodBrandId = "c7419ade-6888-45d2-9066-6291ef92eed1";
        GoodsBrand goodsBrand = goodsBrandSer.findById(goodBrandId);
        goods.setGoodsBrand(goodsBrand);
        goodsSer.update(goods);
    }
}
