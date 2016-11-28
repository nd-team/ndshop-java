package org.ndshop.shop.dao;

import org.ndshop.dbs.jpa.dao.MyRep;
import org.ndshop.shop.dto.CategoryDto;
import org.ndshop.shop.entity.Category;

/**
 * @Author: [caixianyong]
 * @Date: [2016-11-23 16:51]
 * @Description: [店内分类dao]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface ICategoryRep extends MyRep<Category,CategoryDto> {

    Category findByName(String name);
}
