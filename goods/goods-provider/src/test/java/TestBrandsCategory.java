import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.entity.GoodsBrandsCategory;
import org.ndshop.goods.service.IGoodsBrandsCategorySer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品品牌分类业务测试]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestBrandsCategory {
    private static Logger logger = Logger.getLogger(TestBrandsCategory.class);

    @Autowired
    private IGoodsBrandsCategorySer goodsBrandsCategorySer;


    /**
     * 添加商品品牌分类
     *
     * @throws SerException
     */

    @Test
    public void addBrandsCategory() throws SerException {
        String name = "潮流品牌";

        GoodsBrandsCategory goodsBrandsCategory = new GoodsBrandsCategory();
        goodsBrandsCategory.setName(name);
        goodsBrandsCategory.setCreateTime(LocalDateTime.now());
        goodsBrandsCategory.setModifyTime(LocalDateTime.now());
        goodsBrandsCategorySer.save(goodsBrandsCategory);
    }

    @Transactional
    @Test
    public void deleteBrands() throws SerException {
        String id = "b0e1fb72-9b01-44a2-b644-5620f09e1956";
//        goodsBrandsCategorySer.remove(id);
    }


}
