import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.ndshop.goods.service.IGoodsCategorySer;
import org.ndshop.goods.service.IGoodsSecondCategorySer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ike on 16-11-21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestScondCategory {
    private static Logger logger = Logger.getLogger( TestScondCategory.class );

    @Autowired
    private IGoodsSecondCategorySer goodsSecondCategorySer;
    @Autowired
    private IGoodsCategorySer goodsCategorySer;




}
