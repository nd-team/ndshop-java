package org.ndshop.goods.service;

import org.apache.log4j.Logger;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.goods.dto.GoodsSecondCategoryDto;
import org.ndshop.goods.entity.GoodsSecondCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ike on 16-11-21.
 */
@Service
public class GoodsSecondCategorySerImpl extends ServiceImpl<GoodsSecondCategory,GoodsSecondCategoryDto> implements IGoodsSecondCategorySer {
    private static Logger logger = Logger.getLogger(GoodsSecondCategorySerImpl.class);

    @Autowired
    private IGoodsCategorySer goodsCategorySer;


    @Override
    public void addSecondCategory(GoodsSecondCategory goodsSecondCategory, String gcId) throws SerException {

    }

    @Override
    public void findSecondCategory(String gcId) throws SerException {

    }

    @Override
    public void updateSecondCategory(GoodsSecondCategory goodsSecondCategory) throws SerException {

    }

    @Override
    public void addBatchSecondCategory(String firstCategoryName, List<String> name) throws SerException {

    }
}
