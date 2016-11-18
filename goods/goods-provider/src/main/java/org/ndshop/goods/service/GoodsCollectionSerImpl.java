package org.ndshop.goods.service;

import com.alibaba.fastjson.JSON;
import com.dounine.corgi.spring.rpc.Reference;
import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsCollectionDto;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsCollection;
import org.ndshop.goods.enums.CollectionStatus;
import org.ndshop.user.common.entity.User;
import org.ndshop.user.common.service.IUserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by ike on 16-11-15.
 */
@Service
public class GoodsCollectionSerImpl extends ServiceImpl<GoodsCollection, GoodsCollectionDto> implements IGoodsCollectionSer {

    private static Logger logger = Logger.getLogger(GoodsCollectionSerImpl.class);

    @Autowired
    private IGoodsSer goodsSer;
    @Reference
    private IUserSer userSer;

    @Transactional
    @Override
    public void addCollection(String goodsId, String userId) throws SerException {
        GoodsCollection goodsCollection = new GoodsCollection();
        Goods goods = goodsSer.findById(goodsId);
        goodsCollection.setGoods(goods);

        User user = userSer.findById(userId);
        System.out.println(user);
        goodsCollection.setUser(user);

        goodsCollection.setCreateTime(LocalDateTime.now());
        goodsCollection.setModifyTime(LocalDateTime.now());

        save(goodsCollection);
        logger.info(goodsCollection);
    }

    @Transactional
    @Override
    public void updateCollection(String goodsId ,String userId ) throws SerException{
        GoodsCollection goodsCollection = new GoodsCollection();
        Goods goods = new Goods();
        goods.setId( goodsId );
        goodsCollection.setGoods( goods  );

        User user = new User();
        user.setId( userId );
        System.out.println(user);
        goodsCollection.setUser( user );

        GoodsCollectionDto dto = new GoodsCollectionDto();
        Condition c1 = new Condition("id", DataType.STRING,goodsId);
        c1.fieldToModels(Goods.class);
        c1.setRestrict( RestrictionType.EQ );

        Condition c2 = new Condition("id",DataType.STRING,userId);
        c2.fieldToModels(User.class);
        c2.setRestrict( RestrictionType.EQ );

        dto.getConditions().add( c1 );
        dto.getConditions().add( c2 );


        List<GoodsCollection> list = findByCis( dto );
        if( list !=null && list.size() > 0 ) {
            goodsCollection.setStatus(CollectionStatus.CONCEL.name());
            goodsCollection.setCreateTime(list.get(0).getCreateTime());
            goodsCollection.setModifyTime(LocalDateTime.now());
            goodsCollection.setId(list.get(0).getId());

            update(goodsCollection);
        }

    }



    @Override
    public void findCollection(String userId ) throws SerException{
        GoodsCollectionDto dto = new GoodsCollectionDto();

        Condition c = new Condition( "id" , DataType.STRING ,userId );
        c.fieldToModels( User.class );
        c.setRestrict( RestrictionType.EQ );
        dto.getConditions().add( c );

        List<GoodsCollection> gc = findByCis(  dto );
        logger.info(JSON.toJSONString( gc ) );
    }

}
