import com.alibaba.fastjson.JSON;
import com.dounine.corgi.spring.rpc.Reference;
import goods.provider.test.ApplicationConfiguration;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.goods.dto.GoodsCollectionDto;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsCollection;
import org.ndshop.goods.enums.CollectionStatus;
import org.ndshop.goods.service.IGoodsCollectionSer;
import org.ndshop.goods.service.IGoodsSer;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.service.IUserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by ike on 16-11-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestCollection {
    private static Logger logger = Logger.getLogger(TestCollection.class);

    @Autowired
    private IGoodsCollectionSer goodsCollectionSer;
    @Autowired
    private IGoodsSer goodsSer;
    @Reference
    private IUserSer userSer;

    /**
     * 添加收藏
     * @throws SerException
     */
    @Test
    public void addCollection() throws SerException {
        String gid = "b0f42a2a-6c28-42bd-9e5e-969733ae7a84";
        String userid = "drge345456reyrh";
        GoodsCollection goodsCollection = new GoodsCollection();
        Goods goods = new Goods();
        goods.setId( gid );
        goodsCollection.setGoods( goods  );

        User user = new User();
        user.setId( userid );
        System.out.println(user);
        goodsCollection.setUser( user );

        GoodsCollectionDto dto = new GoodsCollectionDto();
        Condition c1 = new Condition("id", DataType.STRING,gid);
        c1.fieldToModels(Goods.class);
        c1.setRestrict( RestrictionType.EQ );

        Condition c2 = new Condition("id",DataType.STRING,gid);
        c2.fieldToModels(Goods.class);
        c2.setRestrict( RestrictionType.EQ );

        dto.getConditions().add( c1 );
        dto.getConditions().add( c2 );


        List<GoodsCollection> list = goodsCollectionSer.findByCis( dto );
        if( list !=null && list.size() > 0 ){
            goodsCollection.setStatus( CollectionStatus.CONCEL.name() );
            goodsCollection.setCreateTime( list.get(0).getCreateTime() );
            goodsCollection.setModifyTime(  LocalDateTime.now() );
            goodsCollection.setId(  list.get(0).getId() );

            goodsCollectionSer.update( goodsCollection );
        }else{
            goodsCollection.setStatus( CollectionStatus.FOUCING.name() );
            goodsCollection.setCreateTime( LocalDateTime.now() );
            goodsCollection.setModifyTime(  LocalDateTime.now() );

            goodsCollectionSer.save( goodsCollection );
        }


    }

    /**
     * 修改收藏的状态  取消收藏 和收藏
     * @throws SerException
     */
    @Test
    public void updateCollection() throws SerException{
        String gid = "b0f42a2a-6c28-42bd-9e5e-969733ae7a84";
        String userid = "drge345456reyrh";
        GoodsCollection goodsCollection = new GoodsCollection();
        Goods goods = new Goods();
        goods.setId( gid );
        goodsCollection.setGoods( goods  );

        User user = new User();
        user.setId( userid );
        System.out.println(user);
        goodsCollection.setUser( user );

        GoodsCollectionDto dto = new GoodsCollectionDto();
        Condition c1 = new Condition("id", DataType.STRING,gid);
        c1.fieldToModels(Goods.class);
        c1.setRestrict( RestrictionType.EQ );

        Condition c2 = new Condition("id",DataType.STRING,gid);
        c2.fieldToModels(Goods.class);
        c2.setRestrict( RestrictionType.EQ );

        dto.getConditions().add( c1 );
        dto.getConditions().add( c2 );


        List<GoodsCollection> list = goodsCollectionSer.findByCis( dto );
        if( list !=null && list.size() > 0 ) {
            goodsCollection.setStatus(CollectionStatus.CONCEL.name());
            goodsCollection.setCreateTime(list.get(0).getCreateTime());
            goodsCollection.setModifyTime(LocalDateTime.now());
            goodsCollection.setId(list.get(0).getId());

            goodsCollectionSer.update(goodsCollection);
        }

    }

    /**
     * 根据用户id查询该用户的商品收藏
     * @throws SerException
     */
    @Test
    public void findCollection() throws SerException{
        String userId = "drge345456reyrh";
        GoodsCollectionDto dto = new GoodsCollectionDto();

        Condition c = new Condition( "id" ,DataType.STRING ,userId );
        c.fieldToModels( User.class );
        c.setRestrict( RestrictionType.EQ );
        dto.getConditions().add( c );

        List<GoodsCollection> gc = goodsCollectionSer.findByCis(  dto );
        logger.info(JSON.toJSONString( gc ) );
    }

}
