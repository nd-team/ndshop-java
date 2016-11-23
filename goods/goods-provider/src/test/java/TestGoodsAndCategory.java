import com.alibaba.fastjson.JSON;
import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.dto.GoodsAndCategoryDto;
import org.ndshop.goods.entity.*;
import org.ndshop.goods.service.IGoodsAndCategorySer;
import org.ndshop.goods.service.IGoodsSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by ike on 16-11-23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestGoodsAndCategory {
    private static Logger logger = Logger.getLogger(TestGoodsAndCategory.class);

    @Autowired
    private IGoodsAndCategorySer goodsAndCategorySer;
    @Autowired
    private IGoodsSer goodsSer;

    /**
     * 添加商品与分类的中间表
     */
    @Test
    public void addGoodsAndCategory() {

        String gid = "f8c812bc-d82c-4ad6-87c2-5b3652f3c026";
        String gFirstCategoryId = "6edf7aa1-86f2-400f-a3dc-22add7154c5a";
        String gSecondCategoryId = "43374db6-42bd-46a9-b2cb-c77c5457d1e2";
        String gThirdCategoryId = "daf2399c-aecd-4933-8d42-ad0e68179153";

        Goods g = new Goods();
        g.setId(gid);

        GoodsCategory gc = new GoodsCategory();//添加一级分类id
        gc.setId(gFirstCategoryId);

        GoodsSecondCategory gsc = new GoodsSecondCategory();//添加二级分类id
        gsc.setId(gSecondCategoryId);

        GoodsThirdCategory gtc = new GoodsThirdCategory();//添加三级分类id
        gtc.setId(gThirdCategoryId);

        GoodsAndCategory gac = new GoodsAndCategory();
        gac.setGoods(g);
        gac.setGoodsCategory(gc);
        gac.setGoodsSecondCategory(gsc);
        gac.setGoodsThirdCategory(gtc);
        gac.setCreateTime(LocalDateTime.now());
        gac.setModifyTime(LocalDateTime.now());

        try {
            goodsAndCategorySer.save(gac);
            logger.info(gac);
        } catch (SerException e) {
            e.printStackTrace();
        } finally {
            logger.error("method:addGoodsAndCategory(), about insert GoodsAndCategory class has error :maybe foriegnkey has null or ‘’ or has same foriegnkey");
        }
    }

    @Transactional
    @Test
    public void findGoodsByFirstCategory() {
        String firstCategoryId = "6edf7aa1-86f2-400f-a3dc-22add7154c5a";

        GoodsAndCategoryDto dto = new GoodsAndCategoryDto();
        Condition c = new Condition("id", DataType.STRING, firstCategoryId);
        c.fieldToModels(GoodsCategory.class);

        dto.getConditions().add(c);

        try {
            List<GoodsAndCategory> gac = goodsAndCategorySer.findByCis(dto);
            logger.info("哈迪斯："+JSON.toJSONString(gac));
            logger.info("哈哈："+JSON.toJSONString(gac.get(0).getGoods()));
        } catch (SerException e) {
            e.printStackTrace();
        } finally {
        }


    }


}


