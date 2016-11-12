package org.ndshop.shop.service;

import org.ndshop.dbs.jpa.service.ServiceImpl;
import org.ndshop.shop.dao.ICategoryRep;
import org.ndshop.shop.dto.CategoryDto;
import org.ndshop.shop.entity.Categroy;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ike on 16-11-12.
 */
public class CategorySerImpl extends ServiceImpl<Categroy,CategoryDto> implements ICategotySer {

    @Autowired
    private ICategoryRep categoryRep;
}
