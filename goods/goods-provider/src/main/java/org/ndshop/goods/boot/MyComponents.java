package org.ndshop.goods.boot;

import org.ndshop.dbs.jpa.boot.initializer.Components;
import org.ndshop.dbs.jpa.boot.initializer.EntityToScan;
import org.springframework.stereotype.Component;



@Component
public class MyComponents extends Components implements EntityToScan {
    @Override
    public String[] entityScan() {
        return new String[]{"org.ndshop.goods","org.ndshop.user"};
    }
}


