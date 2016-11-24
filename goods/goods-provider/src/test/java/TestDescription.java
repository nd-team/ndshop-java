import com.alibaba.fastjson.JSON;
import com.dounine.corgi.cluster.Balance;
import com.dounine.corgi.rpc.RpcApp;
import com.dounine.corgi.spring.rpc.Reference;
import com.dounine.corgi.spring.rpc.ReferenceImpl;
import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsDes;
import org.ndshop.goods.enums.SaleStatus;
import org.ndshop.goods.service.IGoodsSer;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.service.IUserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品描述业务测试]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
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
        Optional<Goods> opGoods = goodsSer.findById( gid );
        Goods goods = opGoods.get();

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
        Optional<Goods> opGoods = goodsSer.findById( goodId );
        Goods goods = opGoods.get();

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
    @Reference
    private IUserSer userSer;
    @Autowired
    protected Balance balance;
    @PostConstruct
    public void initApi(){
        userSer = RpcApp.instance().getProxy(IUserSer.class,new ReferenceImpl(),balance);
    }

    @Test
    public void findDese() throws SerException{
//        String goodId = "b0f42a2a-6c28-42bd-9e5e-969733ae7a84";
//        Goods goods = goodsSer.findById( goodId );
//        GoodsDes goodsDes = goods.getGoodsDes();
//        logger.info(JSON.toJSONString( goodsDes ));
        Optional<List<User>> u = userSer.findAll();
        logger.info(JSON.toJSONString( u.get() ));
    }

}
