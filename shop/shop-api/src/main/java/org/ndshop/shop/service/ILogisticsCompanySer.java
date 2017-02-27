package org.ndshop.shop.service;

import com.alibaba.druid.support.logging.Log;
import com.dounine.corgi.spring.rpc.Service;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.shop.dto.LogisticsCompanyDto;
import org.ndshop.shop.entity.LogisticsCompany;
import org.ndshop.shop.entity.Shop;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: [caixianyong]
 * @Date: [2016-11-28 16:17]
 * @Description: [店铺物流公司的接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface ILogisticsCompanySer extends IService<LogisticsCompany,LogisticsCompanyDto> {

    /**
     * 查询店铺的物流方案,按启用状态排列
     * @param shop  shop的字段id或name
     * @return  物流方案的有序列表
     */
    default List<LogisticsCompany> findByShop(Shop shop) throws SerException {
        return null;
    }

    /**
     * 查询单个物流公司
     * @param shop  shop的字段id或者name
     * @param name  物流公司的name
     * @return  单个物流公司实体
     */
    default LogisticsCompany findByShopAndName(Shop shop,String name) throws SerException {
        return null;
    }
    /**
     * 更改店铺中物流公司的启用状态(供订单选择)
     * @param logisticsCompany  其中的字段id必要
     * @param shop  其中的字段id或者name必要
     * @return
     */
    default boolean changeStatus(@NotNull LogisticsCompany logisticsCompany,@NotNull Shop shop) throws SerException {
        return true;
    }

    /**
     * 为店铺添加支持的物流公司以供选择
     * @param logisticsCompany  完整的物流公司实体
     * @param shop  其中的id或者name
     * @return  保存的物流公司实体
     */
    default LogisticsCompany addLogisComp(LogisticsCompany logisticsCompany,Shop shop) throws SerException {
        return null;
    }

    /**
     * 移除店铺的物流公司
     * @param logisticsCompany 需要的是其中的id
     * @return
     */
    default boolean removeLogisComp(LogisticsCompany logisticsCompany) throws SerException{
        return true;
    }
}
