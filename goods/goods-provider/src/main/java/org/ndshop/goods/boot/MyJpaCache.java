package org.ndshop.goods.boot;


import org.ndshop.dbs.jpa.boot.initializer.JpaCache;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: [lgq]
 * @Date: [2016-11-18 09:04]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Component
public class MyJpaCache implements JpaCache {
    @Override
    public List<Cache> initCaches() {
        ConcurrentMapCache cache =  new ConcurrentMapCache("goodsServiceCache");
        ConcurrentMapCache daoCache =  new ConcurrentMapCache("goodsDaoCache");
        return Arrays.asList(cache,daoCache);
    }
}
