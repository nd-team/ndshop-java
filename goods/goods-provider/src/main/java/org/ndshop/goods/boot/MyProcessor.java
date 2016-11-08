package org.ndshop.goods.boot;

import com.dounine.corgi.rpc.spring.SpringProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by huanghuanlai on 2016/10/12.
 */
@Component
public class MyProcessor extends SpringProcessor {
    @Override
    public boolean exportRpcApp() {
        return false;
    }
}
