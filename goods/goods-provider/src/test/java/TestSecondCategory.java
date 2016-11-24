import com.alibaba.fastjson.JSON;
import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.dto.GoodsSecondCategoryDto;
import org.ndshop.goods.entity.GoodsCategory;
import org.ndshop.goods.entity.GoodsSecondCategory;
import org.ndshop.goods.service.IGoodsCategorySer;
import org.ndshop.goods.service.IGoodsSecondCategorySer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品二级分类业务测试]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestSecondCategory {
    private static Logger logger = Logger.getLogger(TestSecondCategory.class);

    @Autowired
    private IGoodsSecondCategorySer goodsSecondCategorySer;
    @Autowired
    private IGoodsCategorySer goodsCategorySer;

    /**
     * 自我测试：添加二级分类
     * @throws SerException
     */
    @Test
    public void add() throws SerException {
        List<GoodsCategory> gcy = goodsCategorySer.findAll();
        String firstCategoryId = gcy.get(1).getId();
//        List<String> secondName = Arrays.asList("海盗","水果","桌子","电脑");
        List<String> secondName = Arrays.asList("洗衣机");
        for(String str : secondName){
            GoodsSecondCategory goodsSecondCategory = new GoodsSecondCategory();
            goodsSecondCategory.setSecondName( str );
            goodsSecondCategory.setCreateTime(LocalDateTime.now());
            goodsSecondCategory.setModifyTime(LocalDateTime.now());

            GoodsCategory gc = new GoodsCategory();
            gc.setId(firstCategoryId);
            goodsSecondCategory.setGoodsCategory(gc);

            goodsSecondCategorySer.save(goodsSecondCategory);

            logger.info(JSON.toJSONString(goodsSecondCategory));
        }

    }


    /**
     * 添加二级分类
     * @throws SerException
     */
    @Test
    public void addSecondCategory() throws SerException {
        String firstCategoryId = "41a65d18-f204-41e0-8a7e-ea6e761dcaa1";
        String secondName = "海盗";
        GoodsSecondCategory goodsSecondCategory = new GoodsSecondCategory();
        goodsSecondCategory.setSecondName(secondName);
        goodsSecondCategory.setCreateTime(LocalDateTime.now());
        goodsSecondCategory.setModifyTime(LocalDateTime.now());

        GoodsCategory gc = new GoodsCategory();
        gc.setId(firstCategoryId);
        goodsSecondCategory.setGoodsCategory(gc);

        goodsSecondCategorySer.save(goodsSecondCategory);

        logger.info(JSON.toJSONString(goodsSecondCategory));
    }

    /**
     * 更新二级分类的名字
     * @throws SerException
     */
    @Test
    public void updateSecondCategoryName() throws SerException {
        String secondCategoryId = "0bc46188-0e37-43bc-a232-6a91865b8073";
        String secondName = "不齐耳";
        GoodsSecondCategory gsc = goodsSecondCategorySer.findById(secondCategoryId);
        if (gsc != null) {
            gsc.setSecondName(secondName);
            gsc.setModifyTime(LocalDateTime.now());
            goodsSecondCategorySer.update(gsc);
        }

    }

    /**
     * 更改二级分类属于哪一级一级分类
     * @throws SerException
     */
    @Test
    public void updateSecondCategoryForeignKey() throws SerException {
        String secondCategoryId = "0bc46188-0e37-43bc-a232-6a91865b8073";
        String firstCategoryId = "57c68cfc-ae99-4aff-918b-1c913d84289c";

        GoodsSecondCategory gsc = goodsSecondCategorySer.findById(secondCategoryId);

        if (gsc != null) {
            GoodsCategory gc = new GoodsCategory();
            gc.setId(firstCategoryId);
            gsc.setGoodsCategory(gc);
            gsc.setModifyTime(LocalDateTime.now());
            goodsSecondCategorySer.update(gsc);
        }


    }

    /**
     * 更改二级分类的 拼音
     * @throws SerException
     */
    @Test
    public void updateSecondCategoryPinyin() throws SerException {
        String secondCategoryId = "0bc46188-0e37-43bc-a232-6a91865b8073";
        String pinyin = "yuyu";
        GoodsSecondCategory gsc = goodsSecondCategorySer.findById(secondCategoryId);

        if (gsc != null) {
            gsc.setPinyin(pinyin);
            gsc.setModifyTime(LocalDateTime.now());
            goodsSecondCategorySer.update(gsc);
        }


    }

    /**
     * 删除二级分类
     * @throws SerException
     */
    @Test
    public void deleteSecondCategory() throws SerException {
        String secondCategoryId = "438d70c8-4f29-450f-be52-056aa434d202";
        GoodsSecondCategory gsc = goodsSecondCategorySer.findById(secondCategoryId);
        if (gsc != null) {
            goodsSecondCategorySer.remove(secondCategoryId);
        }
    }

    /**
     * 查找一级分类下的所有二级分类
     * @throws SerException
     */
    @Test
    public void findSecondCategoryByGoodsCategory() throws SerException {
        String firstCategoryId = "57c68cfc-ae99-4aff-918b-1c913d84289c";
        GoodsSecondCategoryDto dto = new GoodsSecondCategoryDto();
        Condition c = new Condition("id", DataType.STRING, firstCategoryId);
        c.setRestrict(RestrictionType.EQ);
        c.fieldToModels(GoodsCategory.class);

        dto.getConditions().add(c);
        List<GoodsSecondCategory> gsc = goodsSecondCategorySer.findByCis( dto );
        logger.info(JSON.toJSONString(gsc));
    }

    /**
     * 根据二级分类名查找二级分类
     * @throws SerException
     */
    @Test
    public void findSecondCategoryByName() throws SerException {
        String secondName = "z";

        GoodsSecondCategoryDto dto = new GoodsSecondCategoryDto();
        Condition c = new Condition("secondName", DataType.STRING, secondName);
        c.setRestrict(RestrictionType.LIKE);

        dto.getConditions().add(c);
        List<GoodsSecondCategory> gsc = goodsSecondCategorySer.findByCis( dto );
        logger.info(JSON.toJSONString(gsc));
    }

    /**
     * 根据拼音查找二级分类
     * @throws SerException
     */
    @Test
    public void findSecondCategoryByPinyin() throws SerException {
        String pinyin = "z";

        GoodsSecondCategoryDto dto = new GoodsSecondCategoryDto();
        Condition c = new Condition("pinyin", DataType.STRING, pinyin);
        c.setRestrict(RestrictionType.LIKE);

        dto.getConditions().add(c);
        List<GoodsSecondCategory> gsc = goodsSecondCategorySer.findByCis( dto );
        logger.info(JSON.toJSONString(gsc));
    }

}
