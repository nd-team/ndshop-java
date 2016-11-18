package org.ndshop.dbs.jpa.boot.initializer;


import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lgq on 16-11-18.
 *
 */

public class JpaCacheImpl implements  JpaCache{
    @Override
    public List<Cache> initCaches() {
        return new ArrayList<>(0);
    }
}
