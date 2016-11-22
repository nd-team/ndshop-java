package org.ndshop.shop.service;

import org.ndshop.dbs.jpa.exception.SerException;
import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.shop.dto.LogisticsDto;
import org.ndshop.shop.entity.Logistics;
import org.ndshop.shop.entity.Shop;

import java.util.List;
import java.util.Set;

/**
 * Created by ike on 16-11-21.git
 */
public interface ILogisticsSer extends IService<Logistics,LogisticsDto> {

    default Logistics findByName(String name){
        return null;
    }

    default List<Logistics> findByShop(Shop shop) throws SerException {
        return null;
    }

    default void changeStatus(Logistics logistics) throws SerException {
    }

    default void modifyLogistics(Logistics logistics) throws SerException {
    }

    default void removeLogistics(Logistics logistics) throws SerException {
    }

    default void addLogistics(Logistics logistics,Shop shop){
    }
}
