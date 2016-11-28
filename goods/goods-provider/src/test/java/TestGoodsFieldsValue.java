import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.dao.IGoodsFieldsRep;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsFields;
import org.ndshop.goods.entity.GoodsFieldsValue;
import org.ndshop.goods.service.IGoodsFieldsSer;
import org.ndshop.goods.service.IGoodsFieldsValueSer;
import org.ndshop.goods.service.IGoodsSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-28 16:48]
 * @Description: [商品扩展字段值业务测试]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestGoodsFieldsValue {
    private static Logger logger = Logger.getLogger(TestGoodsFieldsValue.class);

    @Autowired
    private IGoodsFieldsSer goodsFieldsSer ;
    @Autowired
    private IGoodsFieldsRep goodsFieldsRep;
    @Autowired
    private IGoodsSer goodsSer;
    @Autowired
    private IGoodsFieldsValueSer goodsFieldsValueSer ;

    @Test
    public void addGoodsFieldValue() throws SerException{

        String fieldName = "是否可水洗";
        String fielValue = "否";
        String gId = "2a27c9d2-5536-478d-982e-0645731e4679";

        GoodsFieldsValue gfv = new GoodsFieldsValue();

        List<GoodsFields> gf = goodsFieldsSer.findGoodsFields( fieldName );

        Goods goods = new Goods();
        goods.setId( gId);

        gfv.setGoods( goods );
        gfv.setGoodsFields( gf.get(0));
        gfv.setFieldValue( fielValue );
        goodsFieldsValueSer.save( gfv  );
    }

}
