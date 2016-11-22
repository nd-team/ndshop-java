package org.ndshop.shop.service;

import org.ndshop.dbs.jpa.dto.Condition;
import org.ndshop.dbs.jpa.enums.DataType;
import org.ndshop.dbs.jpa.enums.RestrictionType;
import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.shop.dao.ILogisticsRep;
import org.ndshop.shop.dto.LogisticsDto;
import org.ndshop.shop.entity.Logistics;
import org.ndshop.shop.entity.Shop;
import org.ndshop.shop.enums.ShopStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ike on 16-11-21.
 */
public class LogisticsSerImpl extends ServiceImpl<Logistics, LogisticsDto> implements ILogisticsSer {

    @Autowired
    private ILogisticsRep logisticsRep;

    @Override
    public Logistics findByName(String name) {
        return logisticsRep.findByName(name);
    }

    @Override
    public List<Logistics> findByShop(Shop shop) throws SerException {

        LogisticsDto logDto = new LogisticsDto();

        Condition condition = new Condition("shop", DataType.STRING, shop.getName());
        condition.setRestrict(RestrictionType.EQ);

        logDto.getConditions().add(condition);
        logDto.setPage(new Integer(20));

        List<String> sorts = new ArrayList<String>();
        sorts.add("seq");
        logDto.setSorts(sorts);

        return findByCis(logDto, true);

    }

    @Override
    public void changeStatus(Logistics logistics) throws SerException {
        logistics.setStatus(logistics.getStatus() == ShopStatus.OFFLINE ? ShopStatus.ONLINE : ShopStatus.OFFLINE);
        update(logistics);
    }

    @Override
    public void modifyLogistics(Logistics logistics) throws SerException {
        update(logistics);
    }

    @Override
    public void removeLogistics(Logistics logistics) throws SerException {
        remove(logistics);
    }

    @Override
    public void addLogistics(Logistics logistics,Shop shop) {

    }
}
