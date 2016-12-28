package org.ndshop.dbs.jpa.boot.initializer;

import com.alibaba.druid.pool.DruidDataSource;
import org.ndshop.dbs.jpa.boot.Constant;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.support.OpenSessionInViewInterceptor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [配置项扫描类]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class Components {
    @Autowired
    private JpaCache jpaCache;
    @Autowired
    private EntityToScan packagesToScan;

    /**
     * 数据源配置
     *
     * @param env 　配置文件实体
     * @return
     */
    @Bean
    public DataSource getDruid(Environment env) {
        DruidDataSource dds = new DruidDataSource();
        dds.setDriverClassName(env.getProperty("db.driver"));
        dds.setUrl(env.getProperty("db.url"));
        dds.setUsername(env.getProperty("db.username"));
        dds.setPassword(env.getProperty("db.password"));
        return dds;
    }

    /**
     * 实体类管理工厂
     *
     * @param localContainerEntityManagerFactoryBean
     * @return
     */
    @Bean
    public EntityManagerFactory entityManagerFactory(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        return localContainerEntityManagerFactoryBean.getNativeEntityManagerFactory();
    }

    @Bean
    public JpaVendorAdapter hibernateJpaVendorAdapter(){
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        return hibernateJpaVendorAdapter;
    }

    /**
     * 实体管理器
     *
     * @return
     */

    @Bean
    public LocalContainerEntityManagerFactoryBean getLCEMF(DataSource dataSource,JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lcemf = new LocalContainerEntityManagerFactoryBean();
        lcemf.setDataSource(dataSource);
        lcemf.setJpaVendorAdapter(jpaVendorAdapter);
        String[] packages = ArrayUtils.add(packagesToScan.entityScan(), Constant.SCAN_APP_PACKAGES);
        lcemf.setPackagesToScan(packages);
        return lcemf;
    }

//    /**
//     * 事务管理器
//     *
//     * @return
//     */
    @Bean(name = "transactionManager")
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }


    /**
     * 加载缓存配置
     *
     * @return
     */
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<>();
        for (String cache_name : Constant.CACHE_NAME) {
            Cache cache =new ConcurrentMapCache(cache_name);
            cache.put("timeToLiveSeconds",60*60);//1小时过期
            cache.put("timeToIdleSeconds",60*60*12);//闲置时间
            caches.add(cache);
        }
        caches.addAll(jpaCache.initCaches());
        cacheManager.setCaches(caches);
        return cacheManager;
    }


}
