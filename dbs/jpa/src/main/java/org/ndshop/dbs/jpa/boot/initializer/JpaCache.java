package org.ndshop.dbs.jpa.boot.initializer;


import org.springframework.cache.Cache;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lgq on 16-11-18.
 * 添加自己的缓存
 */
public interface JpaCache {
    default List<Cache> initCaches(){
        return new ArrayList<>(0);
    };
}
