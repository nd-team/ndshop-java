package org.ndshop.shop.boot;

import org.ndshop.dbs.jpa.boot.Constant;
import org.ndshop.dbs.jpa.boot.initializer.Components;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lgq on 16-10-13.
 */
@Component("shopComponents")
public class MyComponents extends Components {

    private final static String[] cacheArr={"shopServiceCache","shopDaoCache"};

    @Override
    public String[] scanPackages() {
        return new String[]{"org.ndshop.testshop","org.ndshop.shop","org.ndshop.user.common"};
    }

    @Override
    public List<Cache> initCaches() {
        ConcurrentMapCache serviceCache =  new ConcurrentMapCache("shopServiceCache");
        ConcurrentMapCache daoCache =  new ConcurrentMapCache("shopDaoCache");
        return Arrays.asList(serviceCache,daoCache);
    }
}
