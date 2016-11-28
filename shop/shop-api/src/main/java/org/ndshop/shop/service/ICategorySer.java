package org.ndshop.shop.service;

import org.ndshop.dbs.jpa.service.IService;
import org.ndshop.shop.dto.CategoryDto;
import org.ndshop.shop.entity.Category;
import org.springframework.cache.annotation.Cacheable;

/**
 * @Author: [caixianyong]
 * @Date: [2016-11-23 16:51]
 * @Description: [店内分类service接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface ICategorySer extends IService<Category,CategoryDto>{

    /**
     * 查询对应名字的店内分类
     * @param name  分类名称
     * @return  店内分类实体
     */
    default Category findByName(String name){return null;}
}
