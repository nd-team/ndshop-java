package org.ndshop.dbs.jpa.boot.initializer;

import org.springframework.stereotype.Component;

/**
 * Created by lgq on 16-11-18.
 */
public class EntityToScanImpl implements EntityToScan {

    @Override
    public String[] entityScan() {
        return new String[0];
    }
}
