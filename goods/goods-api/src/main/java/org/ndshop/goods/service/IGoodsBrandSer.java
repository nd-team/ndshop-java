package org.ndshop.goods.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.goods.dto.GoodsBrandDto;
import org.ndshop.goods.entity.GoodsBrand;

/**
 * @Author: [tanghaixiang]
 * @Date: [2016-11-24 09:04]
 * @Description: [商品品牌接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGoodsBrandSer extends IService<GoodsBrand , GoodsBrandDto>{

    /**
     * 添加商品品牌
     * @param brandName 商品品牌名，不能为空
     * @throws SerException
     */
    void addBrand(String brandName ) throws SerException;

    /**
     * 更新品牌
     * @param gb 品牌参数
     * @throws SerException
     */
    void updateBrand (GoodsBrand gb )throws SerException;

    /**
     * 查找品牌
     * @throws SerException
     */
    void findBrand() throws SerException;
}
