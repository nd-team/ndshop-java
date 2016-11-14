package org.ndshop.shop.dao;

import org.ndshop.dbs.jpa.dao.MyRep;
import org.ndshop.shop.dto.CategoryDto;
import org.ndshop.shop.entity.Category;

/**
 * Created by ike on 16-11-12.
 */
public interface ICategoryRep extends MyRep<Category,CategoryDto> {

    Category findByName(String name);
}
