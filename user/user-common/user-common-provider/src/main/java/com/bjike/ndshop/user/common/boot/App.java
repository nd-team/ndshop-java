package com.bjike.ndshop.user.common.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@PropertySource("classpath:config.properties")
@EnableJpaRepositories(basePackages = {"com.bjike.ndshop.user.common"})
@EnableTransactionManagement(proxyTargetClass = true)
public class App {


}
