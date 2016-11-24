import com.alibaba.fastjson.JSON;
import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.dto.GoodsAndCategoryDto;
import org.ndshop.goods.dto.GoodsDto;
import org.ndshop.goods.entity.*;
import org.ndshop.goods.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品和分类业务测试]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestGoodsAndCategory {
    private static Logger logger = Logger.getLogger(TestGoodsAndCategory.class);

    @Autowired
    private IGoodsAndCategorySer goodsAndCategorySer;
    @Autowired
    private IGoodsSer goodsSer;
    @Autowired
    private IGoodsCategorySer goodsCategorySer;
    @Autowired
    private IGoodsSecondCategorySer goodsSecondCategorySer ;
    @Autowired
    private IGoodsThirdCategorySer goodsThirdCategorySer;


    /**
     * 添加商品与分类的中间表
     */
    @Test
    public void addGoodsAndCategory() {

        String gid = "b3044a9d-7042-4b7c-a2bb-77f482a1269f";
        String gFirstCategoryId = "e432ca90-d6b9-459b-a626-bad8370e1cd7";
        String gSecondCategoryId = "6a2e3dad-a19a-4c3f-893c-5510a4c26879";
        String gThirdCategoryId = "45586958-097b-40e2-ae1e-42fc845ee5bd";

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
//            logger.error("method:addGoodsAndCategory(), about insert GoodsAndCategory class has error :maybe foriegnkey has null or ‘’ or has same foriegnkey");
        }
    }

    @Transactional
    @Test
    public void findGoodsByFirstCategory() {
        String firstCategoryId = "28fd7d17-81e1-4187-bc28-5f08bbfe0f9f";

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


    @Transactional
    @Test
    public void findGoodsByCategory() {
        String firstCategoryId = "28fd7d17-81e1-4187-bc28-5f08bbfe0f9f";

        GoodsDto dto = new GoodsDto();
        Condition c = new Condition("id", DataType.STRING, firstCategoryId);
        c.fieldToModels(GoodsAndCategory.class,GoodsCategory.class);

        dto.getConditions().add(c);

        try {
            List<Goods> gac = goodsSer.findByCis(dto);
            logger.info("哈迪斯："+JSON.toJSONString(gac));
//            logger.info("哈哈："+JSON.toJSONString(gac.get(0).getGoods()));
        } catch (SerException e) {
            e.printStackTrace();
        } finally {
        }


    }


}


