package goods.provider.test;

import com.dounine.corgi.rpc.spring.RpcApplicationConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by huanghuanlai on 16/9/27.
 */


@Configuration
@EnableAspectJAutoProxy
@EnableJpaRepositories(  basePackages = {"org.ndshop.goods.dao"})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableCaching
@PropertySource({"classpath:config.properties"})
@ComponentScan(basePackages = {"goods.provider.test","org.ndshop.goods"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class})})
public class ApplicationConfiguration extends RpcApplicationConfiguration{





}


