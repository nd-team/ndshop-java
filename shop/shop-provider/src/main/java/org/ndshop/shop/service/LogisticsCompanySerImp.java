package org.ndshop.shop.service;

import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.shop.dto.LogisticsCompanyDto;
import org.ndshop.shop.entity.LogisticsCompany;
import org.ndshop.shop.entity.Shop;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: [caixianyong]
 * @Date: [2016-11-28 17:26]
 * @Description: [店铺物流公司接口实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class LogisticsCompanySerImp
        extends ServiceImpl<LogisticsCompany,LogisticsCompanyDto>
        implements ILogisticsCompanySer{

    @Override
    public List<LogisticsCompany> findByShop(Shop shop) throws SerException {
        LogisticsCompanyDto logisticsCompanyDto = new LogisticsCompanyDto();
        Condition condition=null;
        if(!StringUtils.isEmpty(shop.getName().trim())){
            condition = new Condition("name", DataType.STRING,shop.getName().trim());
        }else if(!StringUtils.isEmpty(shop.getId().trim())){
            condition = new Condition("id",DataType.STRING,shop.getId().trim());
        }else{
            throw new SerException("shop缺少必要属性:id或者name");
        }
        condition.fieldToModels(Shop.class);
        logisticsCompanyDto.getConditions().add(condition);
        logisticsCompanyDto.setLimit(20);
        logisticsCompanyDto.setPage(1);
        logisticsCompanyDto.setSorts(Arrays.asList("status"));

        List<LogisticsCompany> byCis = findByCis(logisticsCompanyDto);

        return byCis;
    }

    @Override
    public boolean changeStatus() {
        return false;
    }

    @Override
    public LogisticsCompany addLogisComp(LogisticsCompany logisticsCompany, Shop shop) {
        return null;
    }

    @Override
    public boolean removeLogisComp(LogisticsCompany logisticsCompany) {
        return false;
    }
}
