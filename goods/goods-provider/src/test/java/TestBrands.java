import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.dto.GoodsBrandsDto;
import org.ndshop.goods.entity.GoodsBrands;
import org.ndshop.goods.entity.GoodsBrandsCategory;
import org.ndshop.goods.enums.GoodsBrandsRecommendStatus;
import org.ndshop.goods.service.IGoodsBrandsSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-26 15:01]
 * @Description: [商品品牌业务测试]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestBrands {
    private static Logger logger = Logger.getLogger(TestBrands.class);

    @Autowired
    private IGoodsBrandsSer goodsBrandsSer;

    @Test
    public void addBrands() throws SerException{
        GoodsBrands gb = new GoodsBrands();
        gb.setName( "360度衣服" );
        gb.setTrademark("360商标");
        gb.setGoodsBrandsRecommendStatus(GoodsBrandsRecommendStatus.NOTRECOMMEND );
        gb.setKeyWord( "361度");
        gb.setCreateTime( LocalDateTime.now() );
        gb.setModifyTime( LocalDateTime.now() );

        String goodsBrandsCategoryId = "b0e1fb72-9b01-44a2-b644-5620f09e1956";
        GoodsBrandsCategory goodsBrandsCategory = new GoodsBrandsCategory();
        goodsBrandsCategory.setId( goodsBrandsCategoryId );
        gb.setGoodsBrandsCategory(  goodsBrandsCategory );

        goodsBrandsSer.save( gb );
    }

    @Test
    public void findBrands() throws SerException{
        String goodsBrandsCategoryId = "b0e1fb72-9b01-44a2-b644-5620f09e1956";

        GoodsBrandsDto dto = new GoodsBrandsDto();
        Condition c = new Condition("id", DataType.STRING , goodsBrandsCategoryId);
        c.fieldToModels( GoodsBrandsCategory.class );
        dto.getConditions().add( c );
        goodsBrandsSer.findByCis( dto );
    }
}
