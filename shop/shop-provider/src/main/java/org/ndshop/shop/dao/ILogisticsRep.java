package org.ndshop.shop.dao;

import org.ndshop.dbs.jpa.dao.MyRep;
import org.ndshop.shop.dto.LogisticsDto;
import org.ndshop.shop.entity.Logistics;

import java.util.Set;

/**
 * Created by ike on 16-11-21.
 */
public interface ILogisticsRep extends MyRep<Logistics,LogisticsDto> {

    Logistics findByName(String name);


}
