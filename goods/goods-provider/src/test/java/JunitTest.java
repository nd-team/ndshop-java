
import com.bjike.ndshop.dbs.jpa.exception.SerException;
import com.bjike.ndshop.goods.entity.Goods;
import com.bjike.ndshop.goods.entity.GoodsDes;
import com.bjike.ndshop.goods.enums.GoodsCategory;
import com.bjike.ndshop.goods.enums.GoodsElectricType;
import com.bjike.ndshop.goods.service.IGoodsSer;
import com.bjike.ndshop.user.common.entity.User;
import com.bjike.ndshop.user.common.service.IUserSer;
import com.dounine.corgi.spring.rpc.Reference;
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
    @Reference
    private IUserSer userSer;



    @Before
    public  void init () throws SerException {
        if(null == goodsSer.findByGoodName("小苹果手机")  ){
            //temp
            User user =userSer.findByUsername("liguiqin");
            Goods goods = new Goods();
            goods.setGoodsName(user.getId()+"小苹果手机");
            goods.setPrice(2000.0);
            goods.setGoodsLength(3.4);
            goods.setGoodsWidth(1.5);
            goods.setGoodsHeight(5.6);
            goods.setGoodsWeight(0.8);
//            goods.setFirstCategory(GoodsCategory.ELECTRC.toString() );
//            goods.setSecondCategory(GoodsElectricType.AUDIOELECTRIC.toString());
//            GoodsDes goodsDes = new GoodsDes();
//            GoodsInventory goodsInventory = new GoodsInventory();
//            goods.setGoodsDes(goodsDes);
//            goods.setGoodsInventory(goodsInventory);
            goodsSer.save(goods);


        }else{
            User user =userSer.findByUsername("liguiqin");
            Goods gds = goodsSer.findByGoodName(user.getId()+"小苹果手机");
            gds.setFirstCategory(GoodsCategory.ELECTRC.toString() );
            gds.setSecondCategory(GoodsElectricType.AUDIOELECTRIC.toString());
            goodsSer.update(gds);
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


    /**
     * 测试添加商品描述
     */
//    @Test
//    public void addGoodsDes (){
//TODO做验证
        /*if( goodsSer){

        }
        GoodsDes goodsDes = new GoodsDes();
        goodsDes.setShopId();
        goodsDes.setGoodsId();
        goodsDes.setDescription();
        goodsDes.setSaleStatus();
        goodsDes.setCreateTime();
        goodsDes.setModifyTime();
        goodsSer.save(goodsDes);*/
//    }
}
