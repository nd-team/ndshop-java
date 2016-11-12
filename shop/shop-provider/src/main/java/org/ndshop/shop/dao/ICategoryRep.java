package org.ndshop.shop.dao;

import org.ndshop.dbs.jpa.dao.MyRep;
import org.ndshop.shop.dto.CategoryDto;
import org.ndshop.shop.entity.Categroy;

/**
 * Created by ike on 16-11-12.
 */
public interface ICategoryRep extends MyRep<Categroy,CategoryDto> {

    Categroy findByName(String name);
}
