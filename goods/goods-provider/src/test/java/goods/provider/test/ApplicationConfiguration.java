package goods.provider.test;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by huanghuanlai on 16/9/27.
 */


@Configuration
@EnableJpaRepositories(basePackages = {"com.bjike.ndshop.goods.dao"})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableCaching
@PropertySource({"classpath:config.properties","classpath:corgi.properties"})
@ComponentScan(basePackages = {"goods.provider.test","com.bjike.ndshop.goods"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class})})
public class ApplicationConfiguration {

}


