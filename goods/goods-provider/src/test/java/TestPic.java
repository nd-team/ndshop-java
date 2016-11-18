import com.alibaba.fastjson.JSON;
import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.dto.GoodsPicDto;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsPic;
import org.ndshop.goods.service.IGoodsPicSer;
import org.ndshop.goods.service.IGoodsSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by ike on 16-11-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestPic {
    private static Logger logger = Logger.getLogger(TestPic.class);

    @Autowired
    private IGoodsSer goodsSer;
    @Autowired
    private IGoodsPicSer goodsPicSer;

    /**
     * 测试商品图片
     */
    @Test
    public  void addGoodsPic () throws SerException {
        String gid = "b0f7ed15-7e1b-4699-a654-4ae19b4a20b2";
        Goods goods = goodsSer.findById( gid );
        GoodsPic goodsPic = new GoodsPic();
        String time = goodsPic.getCreateTime().minusSeconds(0).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        goodsPic.setPicUrl("/home/xx/xx"+time+".jpg");
        goodsPic.setFlag("详情图片");
        goodsPic.setGoods( goods );
        goodsPicSer.save(  goodsPic );
        logger.info(time+""+JSON.toJSONString( goodsPic ) );

    }

    @Test
    public  void updateGoodsPic () throws  SerException{
        String picId ="3abfab9e-1ebb-4247-8f44-d80c8294e5c1";
        GoodsPic goodsPic = goodsPicSer.findById( picId );
        String time = goodsPic.getCreateTime().minusSeconds(0).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        goodsPic.setPicUrl("/home/xx/xx"+time+".jpg");
        goodsPic.setFlag("详情图片");
        goodsPic.setModifyTime( LocalDateTime.now() );
        goodsPicSer.update(  goodsPic );
        logger.info(time+""+ JSON.toJSONString( goodsPic ) );

    }

    /**
     * 根据商品id查找商品图片
     * @throws SerException
     */
    @Test
    public void findGoodsPic() throws  SerException{
        String gid = "b0f7ed15-7e1b-4699-a654-4ae19b4a20b2";
        Condition condition = new Condition("id", DataType.STRING , gid);
        condition.fieldToModels(Goods.class);
        GoodsPicDto goodsPicDto = new GoodsPicDto();
        condition.setRestrict(RestrictionType.EQ);
        goodsPicDto.getConditions().add( condition );
        List<GoodsPic> gp = goodsPicSer.findByCis( goodsPicDto );
        logger.info( JSON.toJSONString( gp ));
    }

}
