import com.alibaba.fastjson.JSON;
import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.dto.GoodsPicDto;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsPic;
import org.ndshop.goods.service.IGoodsPicSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-26 17:34]
 * @Description: [商品图片业务测试]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class GoodsPicTest {
    private static Logger logger = Logger.getLogger(GoodsPicTest.class);

    @Autowired
    private IGoodsPicSer goodsPicSer;

    @Test
    public void addPic() throws SerException{
        GoodsPic goodsPic = new GoodsPic();
        goodsPic.setName( "衣服图片");
        goodsPic.setBigPic( "big.jpg");
        goodsPic.setSmallPic( "small.jpg");
        goodsPic.setDetail( "衣服图片详情");
        goodsPic.setCreateTime( LocalDateTime.now() );
        goodsPic.setModifyTime( LocalDateTime.now() );

        String goodsId = "";
        Goods g = new Goods();
        g.setId( goodsId );
        goodsPic.setGoods( g );

        goodsPicSer.save( goodsPic );
    }

    @Test
    public void findPicByGoods() throws SerException{
        String goodsId = null;

        Condition c = new Condition("id", DataType.STRING ,goodsId );
        c.fieldToModels(Goods.class );
        GoodsPicDto dto = new GoodsPicDto();
        dto.getConditions().add( c );
        List<GoodsPic> goodsPics = goodsPicSer.findByCis( dto );
        logger.info(JSON.toJSONString( goodsPicSer.findAll() ));
    }
}
