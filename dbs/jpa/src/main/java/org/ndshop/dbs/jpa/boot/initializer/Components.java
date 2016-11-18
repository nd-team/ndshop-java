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
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by huanghuanlai on 16/8/17.
 */
public class Components {

    @Bean
    public DruidDataSource getDruid(Environment env) {
        DruidDataSource dds = new DruidDataSource();
        dds.setDriverClassName(env.getProperty("db.driver"));
        dds.setUrl(env.getProperty("db.url"));
        dds.setUsername(env.getProperty("db.username"));
        dds.setPassword(env.getProperty("db.password"));
        return dds;
    }

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        return localContainerEntityManagerFactoryBean.getNativeEntityManagerFactory();
    }

    /**
     * 实体管理器
     *
     * @return
     */
    @Autowired
    private EntityToScan packagesToScan;

    @Bean(name = "entityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean getLCEMF(DruidDataSource druidDataSource) {
        LocalContainerEntityManagerFactoryBean lcemf = new LocalContainerEntityManagerFactoryBean();
        lcemf.setDataSource(druidDataSource);
        lcemf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        String[] packages = ArrayUtils.add(packagesToScan.entityScan(), Constant.SCAN_APP_PACKAGES);
        lcemf.setPackagesToScan(packages);
        return lcemf;
    }

    /**
     * 事务管理器
     *
     * @return
     */
    @Bean(name = "transactionManager")
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }


    @Bean(name = "openSessionInViewInterceptor")
    public OpenSessionInViewInterceptor openSessionInViewInterceptor(EntityManager entityManager) {
        OpenSessionInViewInterceptor inViewInterceptor = new OpenSessionInViewInterceptor();
        Session session = (Session) entityManager.getDelegate();
        SessionFactory sessionFactory = session.getSessionFactory();
        inViewInterceptor.setSessionFactory(sessionFactory);
        return inViewInterceptor;
    }


    @Autowired
    private JpaCache jpaCache;

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
            caches.add(new ConcurrentMapCache(cache_name));
        }
        caches.addAll(jpaCache.initCaches());
        cacheManager.setCaches(caches);
        return cacheManager;
    }


}
