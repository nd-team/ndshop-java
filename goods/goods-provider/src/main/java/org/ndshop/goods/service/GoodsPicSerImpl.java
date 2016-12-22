package org.ndshop.goods.service;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsPicDto;
import org.ndshop.goods.entity.Goods;
import org.ndshop.goods.entity.GoodsPic;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-26 17:25]
 * @Description: [商品图片业务实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class GoodsPicSerImpl extends ServiceImpl<GoodsPic, GoodsPicDto> implements IGoodsPicSer {
    private static Logger logger = Logger.getLogger(GoodsPicSerImpl.class);


    @Transactional
    @Override
    public void addPic( GoodsPic goodsPic ) throws SerException {
        GoodsPic gp = new GoodsPic();
        gp.setName( goodsPic.getName() );
        gp.setBigPic( goodsPic.getBigPic() );
        gp.setSmallPic(  goodsPic.getSmallPic() );
        gp.setDetail(  goodsPic.getDetail() );
        gp.setCreateTime( LocalDateTime.now() );
        gp.setModifyTime( LocalDateTime.now() );

        save( goodsPic );
    }

    @Cacheable("goodsServiceCache")
    @Override
    public List<GoodsPic> findPicByGoods(String goodsId ) throws SerException{

        Condition c = new Condition("id", DataType.STRING ,goodsId );
//        c.fieldToModels(Goods.class );
        GoodsPicDto dto = new GoodsPicDto();
        dto.getConditions().add( c );
        List<GoodsPic> goodsPics = findByCis( dto );
        logger.info(JSON.toJSONString( goodsPics ) );

        return goodsPics ;
    }
}
