package com.bjike.ndshop.user.register.provider.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * Created by huanghuanlai on 16/8/16.
 */
@Configuration
@ComponentScan(basePackages = "com.bjike.ndshop.user.register.provider",
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class})})
public class App {

}
