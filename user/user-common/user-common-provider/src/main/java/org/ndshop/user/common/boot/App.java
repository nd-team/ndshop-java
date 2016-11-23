package org.ndshop.user.common.boot;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [配置文件扫描类]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Configuration
@EnableJpaRepositories(basePackages = {"org.ndshop.user.common.dao"})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableCaching
@PropertySource({"classpath:config.properties", "classpath:corgi.properties"})
@ComponentScan(basePackages = {"org.ndshop.user.common"},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class})})
public class App {


}
