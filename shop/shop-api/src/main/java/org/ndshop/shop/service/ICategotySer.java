package org.ndshop.shop.service;

import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.shop.dto.CategoryDto;
import org.ndshop.shop.entity.Categroy;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by ike on 16-11-12.
 */
@Cacheable("serviceCache")
public interface ICategotySer extends IService<Categroy,CategoryDto>{

    default Categroy findByName(String name){return null;}
}
