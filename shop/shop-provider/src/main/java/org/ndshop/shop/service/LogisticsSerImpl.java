package org.ndshop.shop.service;

import com.dounine.corgi.spring.rpc.Service;
import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.shop.dao.ILogisticsRep;
import org.ndshop.shop.dao.IShopRep;
import org.ndshop.shop.dto.LogisticsDto;
import org.ndshop.shop.entity.Logistics;
import org.ndshop.shop.entity.Shop;
import org.ndshop.shop.enums.ShopStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

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

    @Override
    public List<Logistics> findByShop(Shop shop) throws SerException {
        LogisticsDto logDto = new LogisticsDto();
        Condition condition = null;

        if (StringUtils.isEmpty(shop.getName().trim())) {
            shop = shopSer.findByName(shop.getName());
        } else if (StringUtils.isEmpty(shop.getId().trim())) {
            shop = shopSer.findById(shop.getId());
        } else {
            throw new SerException("缺少shop必要的属性：name或者id");
        }
        if (shop != null) {
            condition = new Condition("name", DataType.STRING, shop.getName().trim());
            condition.fieldToModels(Shop.class);
            logDto.getConditions().add(condition);
            logDto.setLimit(20);
            logDto.setPage(1);
            logDto.setSorts(Arrays.asList("seq"));
            return findByCis(logDto, true);
        } else {
            throw new SerException("查找不到对应店铺");
        }


    }

    @Override
    public Logistics findByShopAndName(Shop shop, String name) throws SerException {
        LogisticsDto logDto = new LogisticsDto();
        Condition condition1 = null;
        Condition condition2 = null;
        if (StringUtils.isEmpty(shop.getName().trim())) {
            shop = shopSer.findByName(shop.getName().trim());
        } else if (StringUtils.isEmpty(shop.getId().trim())) {
            shop = shopSer.findById(shop.getId().trim());
        } else {
            throw new SerException("缺少shop必要的属性：name或者id");
        }

        if (shop != null) {
            condition1 = new Condition("name",DataType.STRING,shop.getName().trim());
            condition1.fieldToModels(Shop.class);
        }
        if (name != null) {
            condition2 = new Condition("name", DataType.STRING, name.trim());
        } else {
            throw new SerException("name不能为null");
        }

        logDto.getConditions().add(condition1);
        logDto.getConditions().add(condition2);

        Logistics logis = findOne(logDto);

        return logis;
    }

    @Override
    public void changeStatus(Logistics logistics) throws SerException {

        LogisticsDto logisticsDto = new LogisticsDto();
        Condition condition;

        if (StringUtils.isEmpty(logistics.getName().trim())) {
            condition = new Condition("name", DataType.STRING, logistics.getName().trim());
        } else if (StringUtils.isEmpty(logistics.getId().trim())) {
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
        if (StringUtils.isEmpty(logistics.getId().trim())) {
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
    public void addLogistics(Logistics logistics, Shop shop) throws SerException {

        if(StringUtils.isEmpty(shop.getName().trim())){
            shop = shopSer.findByName(shop.getName());
        }else if(StringUtils.isEmpty(shop.getId().trim())){
            shop = shopSer.findById(shop.getId());
        }else {
            throw new SerException("查找不到对应店铺");
        }

        List<Logistics> logisList = findByShop(shop);

        if (logisList.size() == 0 || logisList.stream().filter(logistics1 -> logistics1.getName().equals(logistics.getName())).count() == 0) {
            logistics.setShop(shop);
            if (StringUtils.isEmpty(logistics.getId().trim())) {
                throw new SerException("id必须为空");
            } else {
                save(logistics);
            }
        } else {
            throw new SerException("同个店铺不允许同名物流方案");
        }
    }

    @Override
    public boolean removeLogisticsBatch(List<Logistics> listLogis) {
        remove(listLogis);
        return true;
    }

    @Override
    @Transactional
    public boolean changeStatusBatch(Shop shop, List<String> ids) throws SerException {
        LogisticsDto logisticsDto = new LogisticsDto();
        Condition condition = null;
        if (StringUtils.isEmpty(shop.getId().trim())) {
            condition = new Condition("id", DataType.STRING, shop.getId().trim());
            condition.fieldToModels(Shop.class);
        } else {
            throw new SerException("缺少shop必要属性：id");
        }

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
