package org.ndshop.shop.service;

import com.dounine.corgi.spring.rpc.Service;
import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.shop.dao.ICategoryRep;
import org.ndshop.shop.dto.CategoryDto;
import org.ndshop.shop.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by ike on 16-11-12.
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
