import com.alibaba.fastjson.JSON;
import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.dto.GoodsFieldsDto;
import org.ndshop.goods.entity.GoodsFields;
import org.ndshop.goods.service.IGoodsFieldsSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-28 15:16]
 * @Description: [商品扩展字段业务测试]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestGoodsFields {
    private static Logger logger = Logger.getLogger(TestGoodsFields.class);

    @Autowired
    private IGoodsFieldsSer goodsFieldsSer;

    @Test
    public void addGoodsFields() throws SerException{
        GoodsFields goodsFields = new GoodsFields();
        goodsFields.setFieldName( "是否可水洗");
        goodsFields.setFieldType("String");
        goodsFields.setFieldDescription("水洗描述");
        goodsFields.setCreateTime( LocalDateTime.now() );
        goodsFields.setModifyTime( LocalDateTime.now() );

        goodsFieldsSer.save( goodsFields );

    }

    @Test
    public void findGoodsFields () throws SerException{
        String fieldName = "是否可水洗";

        Condition c = new Condition("fieldName", DataType.STRING ,fieldName );
        GoodsFieldsDto dto = new GoodsFieldsDto();
        dto.getConditions().add( c );
        List<GoodsFields> goodsFields = goodsFieldsSer.findByCis( dto  );
        logger.info(JSON.toJSONString( goodsFields.get(0).getFieldDescription()));
    }
}
