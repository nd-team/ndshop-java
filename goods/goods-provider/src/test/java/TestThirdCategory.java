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
import org.ndshop.goods.service.IGoodsThirdCategorySer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by ike on 16-11-21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestThirdCategory {
    private static Logger logger = Logger.getLogger( TestThirdCategory.class );

    @Autowired
    private IGoodsThirdCategorySer goodsThirdCategorySer;

    @Test
    public void addThirdCategory() throws SerException{
        GoodsThirdCategory goodsThirdCategory = new GoodsThirdCategory();
        goodsThirdCategory.setThirdName("BAB CARE");
        goodsThirdCategory.setPinyin("face");

        String gscId = "43374db6-42bd-46a9-b2cb-c77c5457d1e2";
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

        List<GoodsThirdCategory> gs =  goodsThirdCategorySer.findByCis( dto );
        logger.info(JSON.toJSONString( gs ));
    }

    @Test
    public void findThirdCategoryByPinyin() throws SerException{
        String pinyin = "f";
        GoodsThirdCategoryDto dto = new GoodsThirdCategoryDto();
        Condition c = new Condition("pinyin" , DataType.STRING , pinyin );
        c.setRestrict(RestrictionType.LIKE);
        dto.getConditions().add( c );

        List<GoodsThirdCategory> gs =  goodsThirdCategorySer.findByCis( dto );
        logger.info(JSON.toJSONString( gs ));
    }

    @Test
    public void updateThirdCategory() throws SerException{
        String gtcId = "ac935d92-30d9-4c28-91d2-e2c8185cf79c";
        GoodsThirdCategory gs = goodsThirdCategorySer.findById( gtcId );

        String name ="HAIR CARE";
        if( gs != null ){
            gs.setThirdName(  name );
            gs.setCreateTime( gs.getCreateTime() );
            gs.setModifyTime( LocalDateTime.now() );

            goodsThirdCategorySer.update( gs );
            logger.info( JSON.toJSONString( gs ));
        }
    }

}
