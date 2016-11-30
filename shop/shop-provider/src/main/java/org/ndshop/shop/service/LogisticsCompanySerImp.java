package org.ndshop.shop.service;

import com.dounine.corgi.spring.rpc.Service;
import org.apache.commons.lang3.StringUtils;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.shop.dto.LogisticsCompanyDto;
import org.ndshop.shop.entity.LogisticsCompany;
import org.ndshop.shop.entity.Shop;
import org.ndshop.shop.enums.ShopStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: [caixianyong]
 * @Date: [2016-11-28 17:26]
 * @Description: [店铺物流公司接口实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class LogisticsCompanySerImp
        extends ServiceImpl<LogisticsCompany, LogisticsCompanyDto>
        implements ILogisticsCompanySer {

    @Autowired
    private IShopSer shopSer;

    @Autowired
    private ILogisticsSer logisticsSer;

    @Override
    public List<LogisticsCompany> findByShop(Shop shop) throws SerException {
        LogisticsCompanyDto logisticsCompanyDto = new LogisticsCompanyDto();
        Condition condition = null;
        if (shop.getName() != null && StringUtils.isNotBlank(shop.getName())) {
            condition = new Condition("name", DataType.STRING, shop.getName().trim());
        } else if (shop.getId() != null && StringUtils.isNotBlank(shop.getId())) {
            condition = new Condition("id", DataType.STRING, shop.getId().trim());
        } else {
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
    public LogisticsCompany findByShopAndName(Shop shop, String name) throws SerException {

        Condition condiShop = null;
        Condition condiName = null;

        if (shop != null && StringUtils.isNotBlank(shop.getId())) {
            condiShop = new Condition("id", DataType.STRING, shop.getId().trim());
        } else if (shop != null && StringUtils.isNotBlank(shop.getName())) {
            condiShop = new Condition("name", DataType.STRING, shop.getName().trim());
        } else {
            throw new SerException("缺少shop必要属性id或者name");
        }
        condiShop.fieldToModels(Shop.class);

        if (name != null && StringUtils.isNotBlank(name)) {
            condiName = new Condition("name", DataType.STRING, name.trim());
        }else{
            throw new SerException("name不能为null");
        }

        LogisticsCompanyDto dto = new LogisticsCompanyDto();
        dto.getConditions().add(condiShop);
        dto.getConditions().add(condiName);

        return findOne(dto);
    }

    @Override
    public boolean changeStatus(LogisticsCompany logisticsCompany,Shop shop) throws SerException {

        String logisCompId = logisticsCompany.getId();
        String logisCompName = logisticsCompany.getName();
        String shopId = shop.getId();
        String shopName = shop.getName();

        Condition condition1 = null;
        Condition condition2 = null;

        if (StringUtils.isNotBlank(logisCompId)) {
            condition1 = new Condition("id", DataType.STRING, logisCompId.trim());

        }else if (StringUtils.isNotBlank(logisCompName)) {
            condition1 = new Condition("name", DataType.STRING, logisCompName.trim());

        } else {
            throw new SerException("缺少LogisticsCompany必要的id");
        }

        if (StringUtils.isNotBlank(shopName)) {
            condition2 = new Condition("name", DataType.STRING, shopName.trim());
        } else if (StringUtils.isEmpty(shopId)) {
            condition2 = new Condition("id", DataType.STRING, shopId);
        } else {
            throw new SerException("缺少shop的必要的id或者name");
        }
        condition2.fieldToModels(Shop.class);
        LogisticsCompanyDto dto = new LogisticsCompanyDto();
        dto.getConditions().add(condition1);
        dto.getConditions().add(condition2);

        LogisticsCompany company = findOne(dto);
        if (company.getStatus() == ShopStatus.OFFLINE && company.getLogisticsSet().size() < 1) {
            throw new SerException("启用的物流公司,至少要有一个对应的物流方案");
        } else {
            company.setStatus(company.getStatus() == ShopStatus.OFFLINE ? ShopStatus.ONLINE : ShopStatus.OFFLINE);
        }

        update(company);

        return true;
    }

    @Override
    public LogisticsCompany addLogisComp(LogisticsCompany logisticsCompany, Shop shop) throws SerException {

        String shopId = shop.getId();
        String shopName = shop.getName();
        String logisCompName = logisticsCompany.getName();

        //构造相关条件
        Condition condiShop = null;
        Condition condiLogisComp = null;
        if (StringUtils.isNotBlank(shopName)) {
            condiShop = new Condition("name", DataType.STRING, shopName.trim());
        } else if (StringUtils.isNotBlank(shopId)) {
            condiShop = new Condition("id", DataType.STRING, shopId.trim());
        } else {
            throw new SerException("缺少shop必要属性id或者name");
        }
        condiShop.fieldToModels(Shop.class);
        if (StringUtils.isNotBlank(logisCompName)) {
            condiLogisComp = new Condition("name", DataType.STRING, logisCompName.trim());
        }

        //查找同店铺是否存在同名物流公司
        LogisticsCompanyDto logisticsCompanyDto = new LogisticsCompanyDto();
        logisticsCompanyDto.getConditions().add(condiShop);
        logisticsCompanyDto.getConditions().add(condiLogisComp);
        LogisticsCompany one = findOne(logisticsCompanyDto);
        if (one != null) {
            throw new SerException("已经存在同名物流公司");
        }

       /* //查找店铺的可关联的物流方案
        LogisticsDto logisticsDto = new LogisticsDto();
        logisticsDto.getConditions().add(condiShop);
        List<Logistics> logisticsList = logisticsSer.findByCis(logisticsDto);
        List<Logistics> subList = new ArrayList<>();
        logisticsList.stream().filter(logistics -> logistics.getName().equals(logisticsCompany.getName())).forEach(logistics -> subList.add(logistics));*/

        //保存物流公司
        shop = shopSer.findByName(shopName);
        logisticsCompany.setShop(shop);
        return save(logisticsCompany);
    }

    @Override
    public boolean removeLogisComp(LogisticsCompany logisticsCompany) throws SerException {
        remove(logisticsCompany);
        return true;
    }
}
