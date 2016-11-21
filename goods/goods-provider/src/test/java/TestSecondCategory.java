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
 * Created by ike on 16-11-21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestSecondCategory {
    private static Logger logger = Logger.getLogger( TestSecondCategory.class );

    @Autowired
    private IGoodsSecondCategorySer goodsSecondCategorySer;
    @Autowired
    private IGoodsCategorySer goodsCategorySer;

    @Test
    public void addSecondCategory() throws SerException{
        GoodsSecondCategory goodsSecondCategory = new GoodsSecondCategory();
        goodsSecondCategory.setName("FACE CARE");

        String gcId = "b67c3d21-f5a6-486f-ac5d-6a83cbbf6b93";
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setId( gcId );
        goodsSecondCategory.setGoodsCategory( goodsCategory );

        goodsSecondCategory.setCreateTime( LocalDateTime.now() );
        goodsSecondCategory.setModifyTime( LocalDateTime.now() );

        goodsSecondCategorySer.save(  goodsSecondCategory );
        logger.info( goodsSecondCategory);
    }

    @Test
    public void findSecondCategory() throws SerException{
        String gcId = "b67c3d21-f5a6-486f-ac5d-6a83cbbf6b93";
        GoodsSecondCategoryDto dto = new GoodsSecondCategoryDto();
        Condition c = new Condition("id" , DataType.STRING , gcId );
        c.setRestrict(RestrictionType.EQ);
        c.fieldToModels( GoodsCategory.class );
        dto.getConditions().add( c );

        List<GoodsSecondCategory> gs =  goodsSecondCategorySer.findByCis( dto );
        logger.info(JSON.toJSONString( gs ));
    }

    @Test
    public void updateSecondCategory() throws SerException{
        String gscId = "32ee2b77-157c-4b53-9a5f-f05eb3a18172";
        GoodsSecondCategory gs = goodsSecondCategorySer.findById( gscId );

        String name ="HAIR CARE";
        if( gs != null ){
            gs.setName(  name );
            gs.setCreateTime( gs.getCreateTime() );
            gs.setModifyTime( LocalDateTime.now() );

            goodsSecondCategorySer.update( gs );
            logger.info( JSON.toJSONString( gs ));
        }
    }

    @Test
    public void addBatchSecondCategory() throws SerException{
        String firstCategoryName ="CLOSEBEAUTI";
        List<String> name = Arrays.asList("xixi","bulu","yishun");

        GoodsCategoryDto gcDto = new GoodsCategoryDto();
        Condition c = new Condition("secondName",DataType.STRING,firstCategoryName );
        c.setRestrict(RestrictionType.EQ);
        gcDto.getConditions().add( c );
        List<GoodsCategory> goodsCategory = goodsCategorySer.findByCis( gcDto );

        for( String str : name){
            GoodsSecondCategoryDto gscDto = new GoodsSecondCategoryDto();
            Condition c1 = new Condition("name",DataType.STRING , str);
            c1.setRestrict( RestrictionType.EQ );
            gscDto.getConditions().add( c1 );
            List<GoodsSecondCategory> gsc = goodsSecondCategorySer.findByCis( gscDto );

            if( gsc== null || gsc.size()==0 ){
                GoodsSecondCategory gsct = new GoodsSecondCategory();
                gsct.setName( str );
                gsct.setGoodsCategory( goodsCategory.get(0));
                goodsSecondCategorySer.save( gsct );
                logger.info(JSON.toJSONString( gsct ) );
            }
        }

    }

}
