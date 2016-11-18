package org.ndshop.user.register.boot;


import org.ndshop.dbs.jpa.boot.initializer.JpaCache;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lgq on 16-11-18.
 */
@Component
public class MyJpaCache implements JpaCache {
    @Override
    public List<Cache> initCaches() {
        ConcurrentMapCache cache = new ConcurrentMapCache("userCache");
        return Arrays.asList(cache);
    }
}
