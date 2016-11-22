package org.ndshop.shop.service;

import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.shop.dto.CategoryDto;
import org.ndshop.shop.entity.Category;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by ike on 16-11-12.
 */
public interface ICategorySer extends IService<Category,CategoryDto>{

    default Category findByName(String name){return null;}
}
