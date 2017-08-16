package com.ldongxu.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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
public class AppConfig {

    @Profile("dbcp")
    @Bean("dataSource")
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spring_mybatis_web");
        ds.setUsername("root");
        ds.setPassword("123456");
        ds.setInitialSize(5);
        ds.setMaxWaitMillis(5000);
        ds.setMaxTotal(10);
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
