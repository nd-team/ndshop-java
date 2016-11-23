package org.ndshop.user.common.boot;

import org.ndshop.dbs.jpa.boot.initializer.Components;
import org.ndshop.dbs.jpa.boot.initializer.EntityToScan;
import org.springframework.stereotype.Component;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [entity扫描类]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Component
public class MyComponents extends Components implements EntityToScan{
    @Override
    public String[] entityScan() {
        return new String[]{"org.ndshop.user.common.entity"};
    }
}
