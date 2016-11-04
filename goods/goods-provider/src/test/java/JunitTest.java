import com.bjike.ndshop.dbs.jpa.exception.SerException;


import com.bjike.ndshop.goods.entity.Goods;
import com.bjike.ndshop.goods.service.IGoodsSer;
import goods.provider.test.ApplicationConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by ike on 2016/11/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class JunitTest {

    /**
     * 基础增删改查，批量操作等
     */

    @Autowired
    private IGoodsSer goodsSer;


    @Before
    public  void init () throws SerException{
        if(null == goodsSer.findByGoodName("小苹果手机")  ){
            Goods goods = new Goods();
            goods.setGoodsName("小苹果手机");
            goods.setPrice(2000.0);
            goods.setGoodsLength(3.4);
            goods.setGoodsWidth(1.5);
            goods.setGoodsHeight(5.6);
            goods.setGoodsWeight(0.8);
            goodsSer.save(goods);
        }

    }

    /**
     * 查询全部
     */
    @Test
    public void findAll() throws SerException {
        List<Goods> goods = goodsSer.findAll();
        for(Goods g : goods){
            System.out.println(g.getGoodsName());
        }
    }

}
