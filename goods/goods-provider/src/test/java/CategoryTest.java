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
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [分类业务测试]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class CategoryTest {
    private static Logger logger = Logger.getLogger(CategoryTest.class);

    @Autowired
    private IGoodsCategorySer goodsCategorySer;


    /**
     * 添加分类
     * @throws SerException
     */

    @Test
    public void addParentCategory() throws SerException{
        String categoryName = "电器";
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setName(  categoryName );
        goodsCategory.setParentNodeNum(0L);
        goodsCategory.setCreateTime( LocalDateTime.now() );
        goodsCategory.setModifyTime(LocalDateTime.now() );
        goodsCategorySer.save(  goodsCategory );
    }

    @Test
    public void addChildCategory() throws SerException{
        String parentId = "be11c370-287e-4493-a75e-63e4802919ae";
        String categoryName = "电饭锅";
        GoodsCategory gc = goodsCategorySer.findById( parentId );
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setParent( gc );
        goodsCategory.setName(  categoryName );
        goodsCategory.setCreateTime( LocalDateTime.now() );
        goodsCategory.setModifyTime(LocalDateTime.now() );
        goodsCategory.setParentNodeNum( gc.getParentNodeNum()+1 );
        goodsCategory.setId("测试id");
        goodsCategorySer.save(  goodsCategory );
    }

    /**
     * 测试GoodsServiceImpl的方法
     * @throws SerException
     */
    @Test
    public void addChildCategory2() throws SerException{
        String parentId = "be11c370-287e-4493-a75e-63e4802919ae";
        String categoryName = "mini电饭锅";
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setName(  categoryName );
        goodsCategorySer.addChildCategory(goodsCategory ,parentId);
    }


    /**
     * 查询
     * @throws SerException
     */
    @Test
    public void findCategoryByNodeNum() throws SerException{
        Condition c = new Condition("parentNodeNum", DataType.LONG,2);
        GoodsCategoryDto dto = new GoodsCategoryDto();
        dto.getConditions().add( c );
        List<GoodsCategory> gc = goodsCategorySer.findByCis( dto );

        JSON.toJSONString( gc );
    }

    @Test
    public void findCategoryService() throws SerException {
        goodsCategorySer.findCategoryByNodeNum( 2l );
    }

}
