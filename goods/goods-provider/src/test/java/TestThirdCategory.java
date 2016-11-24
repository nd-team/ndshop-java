import com.alibaba.fastjson.JSON;
import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.dto.GoodsThirdCategoryDto;
import org.ndshop.goods.entity.GoodsSecondCategory;
import org.ndshop.goods.entity.GoodsThirdCategory;
import org.ndshop.goods.service.IGoodsSecondCategorySer;
import org.ndshop.goods.service.IGoodsThirdCategorySer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品三级分类业务测试]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestThirdCategory {
    private static Logger logger = Logger.getLogger( TestThirdCategory.class );

    @Autowired
    private IGoodsThirdCategorySer goodsThirdCategorySer;
    @Autowired
    private IGoodsSecondCategorySer goodsSecondCategorySer;

    /**
     * 自我测试： 添加三级分类
     * @throws SerException
     */
    @Test
    public void add() throws SerException{
        Optional<List<GoodsSecondCategory>> gscy = goodsSecondCategorySer.findAll();
//        String gscId = gscy.get(0).getId();
        String gscId = gscy.get().get(2).getId();
//        List<String> thirdName = Arrays.asList("飞虎队","de hedu","奥斯卡");
        List<String> thirdName = Arrays.asList("大型超市","史蒂夫");
        for(String str : thirdName){
            GoodsThirdCategory goodsThirdCategory = new GoodsThirdCategory();
            goodsThirdCategory.setThirdName( str );
            goodsThirdCategory.setPinyin("face");


            GoodsSecondCategory goodsSecondCategory = new GoodsSecondCategory();
            goodsSecondCategory.setId( gscId );
            goodsThirdCategory.setGoodsSecondCategory( goodsSecondCategory );

            goodsThirdCategory.setCreateTime( LocalDateTime.now() );
            goodsThirdCategory.setModifyTime( LocalDateTime.now() );

            goodsThirdCategorySer.save(  goodsThirdCategory );
            logger.info( goodsThirdCategory);
        }

    }



    /**
     * 添加三级分类
     * @throws SerException
     */
    @Test
    public void addThirdCategory() throws SerException{
        GoodsThirdCategory goodsThirdCategory = new GoodsThirdCategory();
        goodsThirdCategory.setThirdName("GHOE CARE");
        goodsThirdCategory.setPinyin("face");

        String gscId = "36399543-f42b-4af5-942f-77aad42baafb";
        GoodsSecondCategory goodsSecondCategory = new GoodsSecondCategory();
        goodsSecondCategory.setId( gscId );
        goodsThirdCategory.setGoodsSecondCategory( goodsSecondCategory );

        goodsThirdCategory.setCreateTime( LocalDateTime.now() );
        goodsThirdCategory.setModifyTime( LocalDateTime.now() );

        goodsThirdCategorySer.save(  goodsThirdCategory );
        logger.info( goodsThirdCategory);
    }

    @Test
    public void findThirdCategoryBySecondCategory() throws SerException{
        String gscId = "0bc46188-0e37-43bc-a232-6a91865b8073";
        GoodsThirdCategoryDto dto = new GoodsThirdCategoryDto();
        Condition c = new Condition("id" , DataType.STRING , gscId );
        c.setRestrict(RestrictionType.EQ);
        c.fieldToModels( GoodsSecondCategory.class );
        dto.getConditions().add( c );

        Optional<List<GoodsThirdCategory>> gs =  goodsThirdCategorySer.findByCis( dto );
        logger.info(JSON.toJSONString( gs.get() ));
    }

    @Test
    public void findThirdCategoryByPinyin() throws SerException{
        String pinyin = "f";
        GoodsThirdCategoryDto dto = new GoodsThirdCategoryDto();
        Condition c = new Condition("pinyin" , DataType.STRING , pinyin );
        c.setRestrict(RestrictionType.LIKE);
        dto.getConditions().add( c );

        Optional<List<GoodsThirdCategory>> gs =  goodsThirdCategorySer.findByCis( dto );
        logger.info(JSON.toJSONString( gs.get() ));
    }

    @Test
    public void updateThirdCategory() throws SerException{
        String gtcId = "ac935d92-30d9-4c28-91d2-e2c8185cf79c";
        Optional<GoodsThirdCategory> opGoodsThirdCategory = goodsThirdCategorySer.findById( gtcId );

        String name ="HAIR CARE";
        if( opGoodsThirdCategory.isPresent() ){
            GoodsThirdCategory gs = opGoodsThirdCategory.get();
            gs.setThirdName(  name );
            gs.setCreateTime( gs.getCreateTime() );
            gs.setModifyTime( LocalDateTime.now() );

            goodsThirdCategorySer.update( gs );
            logger.info( JSON.toJSONString( gs ));
        }
    }

}
