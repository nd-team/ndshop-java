import com.alibaba.fastjson.JSON;
import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.entity.GoodsBrand;
import org.ndshop.goods.enums.BrandStatus;
import org.ndshop.goods.service.IGoodsBrandSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [品牌业务测试]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestBrands {
    private static Logger logger = Logger.getLogger(TestBrands.class);

    @Autowired
    private IGoodsBrandSer goodsBrandSer;

    /**
     * 添加商品 品牌
     */
    @Test
    public void addBrand() throws SerException {
        String brandName = "费羊羊";
        GoodsBrand goodsBrand = new GoodsBrand();
        goodsBrand.setBrandName(  brandName );
        goodsBrand.setBrandStatus(BrandStatus.FROZEN.getName() );
        goodsBrand.setCreateTime(  LocalDateTime.now() );
        goodsBrand.setModifyTime(  LocalDateTime.now() );
        goodsBrandSer.save( goodsBrand );
    }

    /**
     * 更新品牌名
     * @throws SerException
     */
    @Test
    public void updateBrand ()throws SerException{
        String name = "美羊羊";
        String bid = "4069cb12-bbd0-4641-aa93-de4c811fe2b0";
        Optional<GoodsBrand> opGoodsBrand = goodsBrandSer.findById( bid );
        GoodsBrand goodsBrand = opGoodsBrand.get();
        if (goodsBrand != null ){
            goodsBrand.setBrandName( name );
            goodsBrand.setModifyTime( LocalDateTime.now() );
            goodsBrandSer.update( goodsBrand );
        }
        logger.info ( JSON.toJSONString( goodsBrand ) );
    }

    /**
     * 更新品牌名激活状态
     * @throws SerException
     */
    @Test
    public void updateBrandstatus ()throws SerException{
        String status = BrandStatus.ACTIVE.getName();
        String bid = "4069cb12-bbd0-4641-aa93-de4c811fe2b0";
        Optional<GoodsBrand> opGoodsBrand = goodsBrandSer.findById( bid );
        GoodsBrand goodsBrand = opGoodsBrand.get();
        if (goodsBrand != null ){
            goodsBrand.setBrandStatus( status );
            goodsBrand.setModifyTime( LocalDateTime.now() );
            goodsBrandSer.update( goodsBrand );
        }
        logger.info( JSON.toJSONString ( goodsBrand ) );
    }

    @Test
    public void findBrand() throws SerException{
        Optional<List<GoodsBrand>> opGoodsBrands = goodsBrandSer.findAll();
        logger.info( JSON.toJSONString(  opGoodsBrands.get() ));
    }




}
