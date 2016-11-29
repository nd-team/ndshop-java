package org.ndshop.shop.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.shop.dto.LogisticsDto;
import org.ndshop.shop.entity.Logistics;
import org.ndshop.shop.entity.LogisticsCompany;
import org.ndshop.shop.entity.Shop;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @Author: [caixianyong]
 * @Date: [2016-11-23 16:51]
 * @Description: [物流方案service接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface ILogisticsSer extends IService<Logistics,LogisticsDto> {

    /**
     * 查询具体店铺下所有物流方案
     * @param shop  店铺实体
     * @return  物流方案的list集合
     * @throws SerException
     */
    default List<Logistics> findByShop(Shop shop) throws SerException {
        return null;
    }

    /**
     * 定位具体店铺的具体物流方案
     * @param shop  商店对象
     * @param name  物流方案名称
     * @return  物流方案实体
     * @throws SerException
     */
    default Logistics findByShopAndName(Shop shop,String name) throws SerException {
        return null;
    }

    /**
     * 改变单个物流方案的状态（是否启用）
     * @param logistics
     * @throws SerException
     */
    default void changeStatus(Logistics logistics) throws SerException {
    }

    /**
     * 修改物流方案细节
     * @param logistics 物流方案对象
     * @throws SerException
     */
    default void modifyLogistics(Logistics logistics) throws SerException {
    }

    /**
     * 删除物流方案
     * @param logistics 物流方案实体
     * @throws SerException
     */
    default void removeLogistics(Logistics logistics) throws SerException {
    }

    /**
     * 添加物流方案
     * @param logistics 物流方案实体
     * @param shop  店铺实体其中的id或者name
     * @param logisticsCompany  物流公司实体其中的id
     * @throws SerException
     */
    default void addLogistics(Logistics logistics, Shop shop, LogisticsCompany logisticsCompany) throws SerException {
    }

    /**
     * 批量删除物流方案
     * @param listLogis 物流方案的list集合
     * @return  成功返回true，反之返回false
     */
    default boolean removeLogisticsBatch(List<Logistics> listLogis){
        return true;
    }

    /**
     * 批量改变物流方案的启用状态
     * @param shop  店铺实体
     * @param ids    多个物流方案的id
     * @return  成功返回true，反之返回false
     */
    default boolean changeStatusBatch(Shop shop,List<String> ids) throws SerException {
        return true;
    }

}
