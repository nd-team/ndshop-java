package org.ndshop.shop.boot;

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
@EnableJpaRepositories(basePackages = {"org.ndshop.shop.dao","org.ndshop.user.common.dao"})
@EnableTransactionManagement(proxyTargetClass = true)
    @EnableCaching
    @PropertySource({"classpath:config.properties"})
    @ComponentScan(basePackages = {"org.ndshop.testshop","org.ndshop.shop","org.ndshop.user.common"},
            excludeFilters = {@ComponentScan.Filter(
                    type = FilterType.ANNOTATION,
                    value = {Configuration.class})})
    public class App {

}


