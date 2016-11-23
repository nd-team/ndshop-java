package org.ndshop.dbs.jpa.boot.initializer;


import org.springframework.cache.Cache;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [缓存实现类，默认不实现]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class JpaCacheImpl implements JpaCache {
    @Override
    public List<Cache> initCaches() {
        return new ArrayList<>(0);
    }
}
