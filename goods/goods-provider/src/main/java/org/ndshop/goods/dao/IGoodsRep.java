package org.ndshop.goods.dao;

import com.bjike.ndshop.dbs.jpa.dao.MyRep;
import org.ndshop.goods.dto.GoodsDto;
import org.ndshop.goods.entity.Goods;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by ike on 16-11-4.
 */
public interface IGoodsRep extends MyRep<Goods,GoodsDto> {
    @Cacheable("daoCache")
    Goods findByGoodsName(String gName);

    @Cacheable("daoCache")
    List<Goods> findByFirstCategoryAndSecondCategory (String firstCategory , String secondCategory);
}
