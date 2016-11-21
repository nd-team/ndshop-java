import com.alibaba.fastjson.JSON;
import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.dto.GoodsCategoryDto;
import org.ndshop.goods.dto.GoodsThirdCategoryDto;
import org.ndshop.goods.entity.GoodsCategory;
import org.ndshop.goods.entity.GoodsThirdCategory;
import org.ndshop.goods.service.IGoodsCategorySer;
import org.ndshop.goods.service.IGoodsThirdCategorySer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
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
    @Autowired
    private IGoodsCategorySer goodsCategorySer;

    @Test
    public void addThirdCategory() throws SerException{
        GoodsThirdCategory goodsThirdCategory = new GoodsThirdCategory();
        goodsThirdCategory.setName("FACE CARE");

        String gcId = "b67c3d21-f5a6-486f-ac5d-6a83cbbf6b93";
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setId( gcId );
        goodsThirdCategory.setGoodsCategory( goodsCategory );

        goodsThirdCategory.setCreateTime( LocalDateTime.now() );
        goodsThirdCategory.setModifyTime( LocalDateTime.now() );

        goodsThirdCategorySer.save(  goodsThirdCategory );
        logger.info( goodsThirdCategory);
    }

    @Test
    public void findThirdCategory() throws SerException{
        String gcId = "b67c3d21-f5a6-486f-ac5d-6a83cbbf6b93";
        GoodsThirdCategoryDto dto = new GoodsThirdCategoryDto();
        Condition c = new Condition("id" , DataType.STRING , gcId );
        c.setRestrict(RestrictionType.EQ);
        c.fieldToModels( GoodsCategory.class );
        dto.getConditions().add( c );

        List<GoodsThirdCategory> gs =  goodsThirdCategorySer.findByCis( dto );
        logger.info(JSON.toJSONString( gs ));
    }

    @Test
    public void updateThirdCategory() throws SerException{
        String gscId = "32ee2b77-157c-4b53-9a5f-f05eb3a18172";
        GoodsThirdCategory gs = goodsThirdCategorySer.findById( gscId );

        String name ="HAIR CARE";
        if( gs != null ){
            gs.setName(  name );
            gs.setCreateTime( gs.getCreateTime() );
            gs.setModifyTime( LocalDateTime.now() );

            goodsThirdCategorySer.update( gs );
            logger.info( JSON.toJSONString( gs ));
        }
    }

    @Test
    public void addBatchThirdCategory() throws SerException{
        String firstCategoryName ="CLOSEBEAUTI";
        List<String> name = Arrays.asList("xixi","bulu","yishun");

        GoodsCategoryDto gcDto = new GoodsCategoryDto();
        Condition c = new Condition("secondName",DataType.STRING,firstCategoryName );
        c.setRestrict(RestrictionType.EQ);
        gcDto.getConditions().add( c );
        List<GoodsCategory> goodsCategory = goodsCategorySer.findByCis( gcDto );

        for( String str : name){
            GoodsThirdCategoryDto gscDto = new GoodsThirdCategoryDto();
            Condition c1 = new Condition("name",DataType.STRING , str);
            c1.setRestrict( RestrictionType.EQ );
            gscDto.getConditions().add( c1 );
            List<GoodsThirdCategory> gsc = goodsThirdCategorySer.findByCis( gscDto );

            if( gsc== null || gsc.size()==0 ){
                GoodsThirdCategory gsct = new GoodsThirdCategory();
                gsct.setName( str );
                gsct.setGoodsCategory( goodsCategory.get(0));
                goodsThirdCategorySer.save( gsct );
                logger.info(JSON.toJSONString( gsct ) );
            }
        }

    }

}
