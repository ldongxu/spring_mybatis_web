package com.ldongxu.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by 刘东旭 on 2017/8/16.
 */
@Configuration
@ComponentScan(basePackages = {"com.ldongxu"},
        excludeFilters = {@Filter(EnableWebMvc.class), @Filter(Controller.class)})
@MapperScan(basePackages = {"com.ldongxu.mapper"})
@PropertySource("classpath:/jdbc.properties")
public class AppConfig {
    @Autowired
    private Environment evn;

    @Profile("dbcp")
    @Bean("dataSource")
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(evn.getProperty("jdbc.driver"));
        ds.setUrl(evn.getProperty("jdbc.driverUrl"));
        ds.setUsername(evn.getProperty("jdbc.user"));
        ds.setPassword(evn.getProperty("jdbc.password"));
        ds.setInitialSize(evn.getProperty("jdbc.initialSize",Integer.class));
        ds.setMaxWaitMillis(evn.getProperty("jdbc.maxWaitMillis",Integer.class));
        ds.setMaxTotal(evn.getProperty("jdbc.maxTotal",Integer.class));
        return ds;
    }

    @Profile("jndi")
    @Bean("dataSource")
    public JndiObjectFactoryBean dataSource2() {
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("jndi/mybatis");
        jndiObjectFactoryBean.setResourceRef(true);
        jndiObjectFactoryBean.setProxyInterface(DataSource.class);
        return jndiObjectFactoryBean;
    }


    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setTypeAliasesPackage("com.ldongxu.model");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(true);
        configuration.setLazyLoadingEnabled(true);
        configuration.setAggressiveLazyLoading(false);
        configuration.setLogImpl(Slf4jImpl.class);
        configuration.setUseGeneratedKeys(true);
        sessionFactoryBean.setConfiguration(configuration);
        return sessionFactoryBean;
    }

}
