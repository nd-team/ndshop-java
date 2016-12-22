package user_register_code;

import com.dounine.corgi.rpc.spring.RpcApplicationConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [测试配置项]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Configuration
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = {"org.ndshop.user.common.dao"})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableCaching
@PropertySource({"classpath:config.properties"})
@ComponentScan(basePackages = {"user_register_code","org.ndshop.user.common","org.ndshop.user.register"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class})})
public class ApplicationConfiguration extends RpcApplicationConfiguration{

}

