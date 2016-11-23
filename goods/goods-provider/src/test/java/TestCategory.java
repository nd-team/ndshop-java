import com.alibaba.fastjson.JSON;
import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.dao.IGoodsCategoryRep;
import org.ndshop.goods.dto.GoodsCategoryDto;
import org.ndshop.goods.entity.GoodsCategory;
import org.ndshop.goods.service.IGoodsCategorySer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
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

    @Autowired
    private IGoodsCategoryRep goodsCategoryRep;

    /**
     * 添加分类
     * @throws SerException
     */

    @Test
    public void addCategory() throws SerException{
        String categoryName = "MONA";
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setName(  categoryName );
        goodsCategory.setCreateTime( LocalDateTime.now() );
        goodsCategory.setModifyTime(LocalDateTime.now() );
        goodsCategorySer.save(  goodsCategory );
    }

    @Test
    public void addBatchCategory() throws SerException{
        List<String> category = Arrays.asList("HUHU","BUBU","ZK");
        for( String str : category){
            GoodsCategoryDto dto = new GoodsCategoryDto();
            Condition c = new Condition("name",DataType.STRING , str );
            c.setRestrict(RestrictionType.EQ);
            dto.getConditions().add( c );
            List<GoodsCategory> gc = goodsCategorySer.findByCis( dto );

            if( gc== null || gc.size()==0 ){
                GoodsCategory gct = new GoodsCategory();
                gct.setName( str );
                goodsCategorySer.save( gct );
                logger.info(JSON.toJSONString( gct ) );
            }
        }

    }

    /**
     * 更新分类
     * @throws SerException
     */
    @Test
    public void updateCategory() throws SerException{
        String cateoryId ="b3ecbadd-bdbc-4a9f-b121-e04faa19a672";
        String categoryName = "其他";

        GoodsCategory goodsCategory = goodsCategorySer.findById( cateoryId );
        goodsCategory.setModifyTime( LocalDateTime.now() );
        goodsCategory.setId( cateoryId );
        goodsCategory.setName( categoryName );
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
        condition.setRestrict(RestrictionType.LIKE);
        GoodsCategoryDto dto = new GoodsCategoryDto();
        dto.getConditions().add( condition );
        dto.setLimit(2);
        dto.setPage(1);
        dto.setSorts(Arrays.asList("modifyTime"));
        List<GoodsCategory> goodCategory = goodsCategorySer.findByCis( dto,true );
        logger.info( JSON.toJSONString(goodCategory) );

    }

    @Test
    public void deleteCategory() throws SerException{
        String ci ="0582cf35-b47a-494e-93fb-c3d4e1573d9e";
        goodsCategorySer.remove(  ci );
    }


    @Test
    public void finds()throws SerException{
        goodsCategorySer.findCategoryByFirstCategory("");
    }

}
