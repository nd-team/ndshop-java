import com.alibaba.fastjson.JSON;
import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.dto.GoodsCategoryDto;
import org.ndshop.goods.entity.GoodsCategory;
import org.ndshop.goods.service.IGoodsCategorySer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by ike on 16-11-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestCategory {
    private static Logger logger = Logger.getLogger(TestCategory.class);

    @Autowired
    private IGoodsCategorySer goodsCategorySer;


    /**
     * 添加分类
     * @throws SerException
     */

    @Test
    public void addCategory() throws SerException{
        String categoryName = "BEAUTI";
        String secondName = "FACEBEAUTI";
        String thirdName = "HAIR CARE";
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setName(  categoryName );
        goodsCategory.setSecondName( secondName );
        goodsCategory.setThirdName( thirdName );
        goodsCategory.setCreateTime( LocalDateTime.now() );
        goodsCategory.setModifyTime(LocalDateTime.now() );
        goodsCategorySer.save(  goodsCategory );
    }

    /**
     * 更新分类
     * @throws SerException
     */
    @Test
    public void updateCategory() throws SerException{
        String cateoryId ="88fe1f03-1e6b-44e5-a09f-ebbb880d5cce";
        String categoryName = "BEAUTI";
        String secondName = "FACEBEAUTI";
        String thirdName = "HAIR CARE";

        GoodsCategory goodsCategory = goodsCategorySer.findById( cateoryId );
        if ( categoryName != "" &&  categoryName != null) {
            goodsCategory.setSecondName( categoryName );
        }
        if ( secondName !="" && secondName != null ) {
            goodsCategory.setSecondName( secondName );
        }
        if ( thirdName != "" && thirdName != null ) {
            goodsCategory.setThirdName( thirdName );
        }
        goodsCategory.setModifyTime( LocalDateTime.now() );
        goodsCategory.setId( cateoryId );
        goodsCategorySer.update( goodsCategory );
        logger.info( JSON.toJSONString( goodsCategory ) );
    }


    /**
     * 根据一级查找分类
     * @throws SerException
     */
    @Test
    public void findCategoryByFirstCategory () throws  SerException{
        String name = "BEAUTI";
        Condition condition = new Condition("name", DataType.STRING ,name);
        GoodsCategoryDto dto = new GoodsCategoryDto();
        dto.getConditions().add( condition );
        dto.setLimit(2);
        dto.setPage(1);
        List<GoodsCategory> goodCategory = goodsCategorySer.findByCis( dto,true );
        logger.info(JSON.toJSONString(goodCategory) );
    }

    @Test
    public void deleteCategory() throws SerException{
        String ci ="c1033ab2-3338-472d-aeef-2f0fcb049129";
        goodsCategorySer.remove(  ci );
    }

}