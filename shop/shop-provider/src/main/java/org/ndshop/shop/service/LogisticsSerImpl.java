package org.ndshop.shop.service;

import com.dounine.corgi.spring.rpc.Service;
import org.apache.commons.lang3.StringUtils;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.shop.dao.ILogisticsRep;
import org.ndshop.shop.dto.LogisticsCompanyDto;
import org.ndshop.shop.dto.LogisticsDto;
import org.ndshop.shop.entity.Logistics;
import org.ndshop.shop.entity.LogisticsCompany;
import org.ndshop.shop.entity.Shop;
import org.ndshop.shop.enums.ShopStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: [caixianyong]
 * @Date: [2016-11-23 16:51]
 * @Description: [物流方案service实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class LogisticsSerImpl extends ServiceImpl<Logistics, LogisticsDto> implements ILogisticsSer {

    @Autowired
    private ILogisticsRep logisticsRep;

    @Autowired
    private IShopSer shopSer;

    @Autowired
    private ILogisticsCompanySer logisticsCompanySer;

    @Override
    public List<Logistics> findByShop(Shop shop) throws SerException {
        LogisticsDto logDto = new LogisticsDto();
        Condition condition = null;

        shop = shop == null ? (new Shop()) : shop;

        if (StringUtils.isNotBlank(shop.getName())) {
            shop = shopSer.findByName(shop.getName());
        } else if (StringUtils.isNotBlank(shop.getId())) {
            shop = shopSer.findById(shop.getId());
        } else {
            throw new SerException("缺少shop必要的属性：name或者id");
        }

        //判断返回的shop
        if (shop != null) {
            condition = new Condition("name", DataType.STRING, shop.getName().trim());
            condition.fieldToModels(Shop.class);

            logDto.getConditions().add(condition);
            logDto.setLimit(20);
            logDto.setPage(1);
            logDto.setSorts(Arrays.asList("expressComp", "seq"));
            return findByCis(logDto, true);
        } else {
            throw new SerException("查找不到对应店铺");
        }


    }

    @Override
    public Logistics findByShopAndName(Shop shop, String name) throws SerException {
        LogisticsDto logDto = new LogisticsDto();
        Condition condiShop = null;
        Condition condiName = null;

        shop = shop == null ? (new Shop()) : shop;

        if (StringUtils.isNotBlank(shop.getName())) {
            shop = shopSer.findByName(shop.getName().trim());
        } else if (StringUtils.isNotBlank(shop.getId())) {
            shop = shopSer.findById(shop.getId().trim());
        } else {
            throw new SerException("缺少shop必要的属性：name或者id");
        }

        if (shop != null) {
            condiShop = new Condition("name", DataType.STRING, shop.getName().trim());
            condiShop.fieldToModels(Shop.class);
        }else{
            throw new SerException("查找不到对应店铺");
        }

        if (name != null) {
            condiName = new Condition("name", DataType.STRING, name.trim());
        } else {
            throw new SerException("name不能为null");
        }

        logDto.getConditions().add(condiShop);
        logDto.getConditions().add(condiName);

        return findOne(logDto);

    }

    @Override
    public void changeStatus(Logistics logistics) throws SerException {

        LogisticsDto logisticsDto = new LogisticsDto();
        Condition condition = null;

        logistics = logistics == null ? (new Logistics()) : logistics;

        if (StringUtils.isNotBlank(logistics.getName())) {
            condition = new Condition("name", DataType.STRING, logistics.getName().trim());
        } else if (StringUtils.isNotBlank(logistics.getId())) {
            condition = new Condition("id", DataType.STRING, logistics.getId().trim());
        } else {
            throw new SerException("缺少logistics必要属性：name或者id");
        }

        logisticsDto.getConditions().add(condition);
        Logistics logis = findOne(logisticsDto);

        if (logis == null) {
            throw new SerException("参数错误，查找不到对应物流方案");
        } else {
            logis.setStatus(logis.getStatus() == ShopStatus.OFFLINE ? ShopStatus.ONLINE : ShopStatus.OFFLINE);
            update(logis);
        }

    }

    @Override
    public void modifyLogistics(Logistics logistics) throws SerException {
        LogisticsDto logisticsDto = new LogisticsDto();
        Condition condition;

        logistics = logistics == null ? (new Logistics()) : logistics;

        if (StringUtils.isNotBlank(logistics.getId())) {
            condition = new Condition("id", DataType.STRING, logistics.getId().trim());
        } else {
            throw new SerException("缺少logistics必要属性：id");
        }
        logisticsDto.getConditions().add(condition);
        Logistics logis = findOne(logisticsDto);

        if (logis == null) {
            throw new SerException("查找不到此物流方案");
        } else {
            update(logistics);
        }
    }

    @Override
    public void removeLogistics(Logistics logistics) throws SerException {
        remove(logistics);
    }

    @Override
    @Transactional
    public void addLogistics(Logistics logistics, Shop shop, LogisticsCompany logisticsCompany) throws SerException {

        if (shop == null || logistics == null || logisticsCompany == null) {
            throw new SerException("无效参数");
        }

        if (StringUtils.isNotBlank(shop.getName())) {
            shop = shopSer.findByName(shop.getName().trim());
        } else if (StringUtils.isNotBlank(shop.getId())) {
            shop = shopSer.findById(shop.getId().trim());
        } else {
            throw new SerException("缺少shop必要的属性id或者name");
        }

        //判断查找结果
        if (shop == null) {
            throw new SerException("查找不到对应店铺");
        }

        if (StringUtils.isNotBlank(logisticsCompany.getId())) {
            logisticsCompany = logisticsCompanySer.findById(logisticsCompany.getId().trim());
        } else if (StringUtils.isNotBlank(logisticsCompany.getName())) {
            Condition condiComp = new Condition("name", DataType.STRING, logisticsCompany.getName().trim());
            Condition condiShop = new Condition("id", DataType.STRING, shop.getId().trim());
            condiShop.fieldToModels(Shop.class);
            LogisticsCompanyDto dto = new LogisticsCompanyDto();
            dto.getConditions().add(condiComp);
            dto.getConditions().add(condiShop);

            logisticsCompany = logisticsCompanySer.findOne(dto);
        } else {
            throw new SerException("缺少logisticsCompany必要的属性id或者name");
        }

        //判断查找结果
        if (logisticsCompany == null) {
            throw new SerException("查找不到对应物流公司");
        }

        //筛选
        List<Logistics> logisList = findByShop(shop);

        if (logisList.size() == 0 || logisList.stream().filter(logistics1 -> logistics1.getName().equals(logistics.getName())).count() == 0) {
            logistics.setShop(shop);
            if (StringUtils.isNotBlank(logistics.getId())) {
                throw new SerException("id必须为空");
            } else {
                //关联物流公司
                logistics.setExpressComp(logisticsCompany);
                save(logistics);
            }
        } else {
            throw new SerException("同个店铺不允许同名物流方案");
        }
    }

    @Override
    public boolean removeLogisticsBatch(List<Logistics> listLogis) {
        if (listLogis != null) {
            remove(listLogis);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean changeStatusBatch(Shop shop, List<String> ids) throws SerException {
        LogisticsDto logisticsDto = new LogisticsDto();
        Condition condition = null;

        shop = shop == null ? (new Shop()) : shop;

        if (StringUtils.isNotBlank(shop.getId())) {
            condition = new Condition("id", DataType.STRING, shop.getId().trim());
        }else if (StringUtils.isNotBlank(shop.getName())) {
            condition = new Condition("name", DataType.STRING, shop.getName().trim());
        } else {
            throw new SerException("缺少shop必要属性：id或者name");
        }

        condition.fieldToModels(Shop.class);
        logisticsDto.getConditions().add(condition);

        List<Logistics> list = findByCis(logisticsDto);
        List<Logistics> subList = new ArrayList<Logistics>();

        for (Logistics log : list) {
            String id = log.getId();
            if (ids.contains(id)) {
                log.setStatus(log.getStatus() == ShopStatus.OFFLINE ? ShopStatus.ONLINE : ShopStatus.OFFLINE);
                subList.add(log);
            }
        }
        update(subList);
        return true;
    }
}
