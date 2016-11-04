package com.bjike.ndshop.user.register.boot;

import com.dounine.corgi.rpc.spring.SpringProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by huanghuanlai on 16/10/10.
 */
@Component
public class MyProcessor extends SpringProcessor {

    @Override
    public boolean exportRpcApp() {
        return false;
    }
}
