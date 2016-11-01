package com.bjike.ndshop.user.login.provider.service.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * Created by huanghuanlai on 16/8/16.
 */
@Configuration
@ComponentScan(basePackages = "com.dounine.corgi.demo",
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class})})
public class App {


}
