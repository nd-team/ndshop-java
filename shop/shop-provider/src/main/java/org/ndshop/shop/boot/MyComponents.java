package org.ndshop.shop.boot;

import org.ndshop.dbs.jpa.boot.initializer.Components;
import org.ndshop.dbs.jpa.boot.initializer.EntityToScan;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lgq on 16-10-13.
 */
@Component("shopComponents")
public class MyComponents extends Components implements EntityToScan {

    @Override
    public String[] entityScan() {
        return new String[]{"org.ndshop.testshop","org.ndshop.shop","org.ndshop.user.common"};
    }
}
