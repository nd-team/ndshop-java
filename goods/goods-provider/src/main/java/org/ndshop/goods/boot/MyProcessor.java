package org.ndshop.goods.boot;

import com.dounine.corgi.rpc.spring.SpringProcessor;
import org.springframework.stereotype.Component;
/**
 * @Author: [huanghuanlai]
 * @Date: [2016-10-12 09:04]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Component
public class MyProcessor extends SpringProcessor {
    @Override
    public boolean exportRpcApp() {
        return false;
    }
}
