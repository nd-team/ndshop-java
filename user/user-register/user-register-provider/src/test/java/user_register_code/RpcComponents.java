package user_register_code;

import com.bjike.ndshop.dbs.jpa.boot.initializer.Components;
import org.springframework.stereotype.Component;

/**
 * Created by lgq on 16-10-13.
 */
@Component
public class RpcComponents extends Components {

    @Override
    public String[] scanPackages() {
        return new String[]{"com.bjike.ndshop.user.common",
                "com.bjike.ndshop.user.register"};
    }


}