import com.alibaba.fastjson.JSON;
import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.dto.GoodsDto;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsDes;
import org.ndshop.goods.entity.GoodsInventory;
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
public class TestInventory {
    private static Logger logger = Logger.getLogger(TestInventory.class);

    @Autowired
    private IGoodsSer goodsSer;

    @Test
    public void addInventory() throws SerException{
        String gid = "81861f34-9af6-4858-96b4-4b7c7c67b587";
        Goods goods = goodsSer.findById( gid );

        GoodsInventory gi = new GoodsInventory();
        gi.setQuanty( 199L);
        gi.setHasSaleQuanty( 20L);
        gi.setCreateTime( LocalDateTime.now() );
        gi.setModifyTime( LocalDateTime.now() );

        GoodsInventory goodsInventory = goods.getGoodsInventory();
        if( goodsInventory == null ){
            gi.setGoods(goods);
            goods.setGoodsInventory(gi);
            goodsSer.update( goods );
        }else{
            goodsInventory.setQuanty( gi.getQuanty() );
            goodsInventory.setHasSaleQuanty( gi.getHasSaleQuanty() );
            goodsInventory.setModifyTime( LocalDateTime.now() );
            goodsInventory.setGoods( goods );
            goodsSer.update( goods );
        }
    }

    @Test
    public void updateGoodsInventorys() throws SerException {
        String goodId = "81861f34-9af6-4858-96b4-4b7c7c67b587";
        Goods goods = goodsSer.findById( goodId );

        GoodsInventory goodsInventory =  new GoodsInventory();
        goodsInventory.setQuanty( 100L );
        goodsInventory.setHasSaleQuanty( 12l);
        if( goods.getGoodsDes()!=null){
            goodsInventory.setCreateTime( goods.getGoodsInventory().getCreateTime() );
            goodsInventory.setId( goods.getGoodsInventory().getId() );
        }else{
            goodsInventory.setCreateTime( LocalDateTime.now() );
            goodsInventory.setId( goodId );
        }
        goodsInventory.setModifyTime( LocalDateTime.now() );
        goodsInventory.setGoods(  goods );

        goods.setGoodsInventory( goodsInventory );
        goodsSer.update(  goods );

    }

    @Test
    public void findInventory() throws SerException{
        String goodId = "81861f34-9af6-4858-96b4-4b7c7c67b587";

        Goods goods = goodsSer.findById( goodId );
        GoodsInventory goodsInventory = goods.getGoodsInventory();
        logger.info(  JSON.toJSONString( goodsInventory) );
    }
}
