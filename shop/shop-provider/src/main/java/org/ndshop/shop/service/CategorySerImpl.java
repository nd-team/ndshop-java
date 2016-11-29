package org.ndshop.shop.service;

import com.dounine.corgi.spring.rpc.Service;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.shop.dao.ICategoryRep;
import org.ndshop.shop.dto.CategoryDto;
import org.ndshop.shop.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Author: [caixianyong]
 * @Date: [2016-11-23 16:51]
 * @Description: [店内分类service实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Service
public class CategorySerImpl extends ServiceImpl<Category,CategoryDto> implements ICategorySer {

    @Autowired
    private ICategoryRep categoryRep;

    @Override
    public Category findByName(String name) {
        return categoryRep.findByName(name);
    }
}
