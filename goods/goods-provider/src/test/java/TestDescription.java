import com.alibaba.fastjson.JSON;
import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsDes;
import org.ndshop.goods.enums.SaleStatus;
import org.ndshop.goods.service.IGoodsSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

/**
 * Created by ike on 16-11-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestDescription {
    private static Logger logger = Logger.getLogger(TestDescription.class);

    @Autowired
    private IGoodsSer goodsSer;

    @Test
    public void addDescription() throws SerException{
        String gid = "b0f42a2a-6c28-42bd-9e5e-969733ae7a84";
        Goods goods = goodsSer.findById( gid );

        GoodsDes gDes = new GoodsDes();
        gDes.setSaleStatus( SaleStatus.ONSHELF );

        GoodsDes goodsDes = goods.getGoodsDes();
        if( goodsDes == null ){
            gDes.setGoods(goods);
            goods.setGoodsDes(gDes);
            goodsSer.update( goods );
        }else{
            goodsDes.setSaleStatus( gDes.getSaleStatus() );
            goodsDes.setModifyTime( LocalDateTime.now() );
            goodsDes.setGoods( goods );
            goodsSer.update( goods );
        }

    }

    @Test
    public void updateGoodsDes() throws SerException{
        String goodId = "b0f42a2a-6c28-42bd-9e5e-969733ae7a84";
        Goods goods = goodsSer.findById( goodId );

        GoodsDes goodsDes = new GoodsDes();
        goodsDes.setDescription("乐视超4 X50 乐视TV X3-50 UHD液晶平板电视机50英寸3D 4K超3 48");
        goodsDes.setSaleStatus(SaleStatus.ONSHELF);
        goodsDes.setModifyTime(LocalDateTime.now());
        if( goods.getGoodsDes()!=null){
            goodsDes.setCreateTime( goods.getGoodsDes().getCreateTime() );
            goodsDes.setId( goods.getGoodsDes().getId() );
        }else{
            goodsDes.setCreateTime( LocalDateTime.now() );
            goodsDes.setId( goodId );
        }

        goodsDes.setGoods( goods );

        goods.setGoodsDes(goodsDes);
        goodsSer.update( goods );
    }

    /**
     * 查找描述
     * @throws SerException
     */

    @Test
    public void findDese() throws SerException{
        String goodId = "b0f42a2a-6c28-42bd-9e5e-969733ae7a84";
        Goods goods = goodsSer.findById( goodId );
        GoodsDes goodsDes = goods.getGoodsDes();
        logger.info(JSON.toJSONString( goodsDes ));
    }

}
