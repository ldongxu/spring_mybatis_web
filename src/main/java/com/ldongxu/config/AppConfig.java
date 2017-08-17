package com.ldongxu.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * Created by 刘东旭 on 2017/8/16.
 */
@Configuration
@ComponentScan(basePackages = {"com.ldongxu"},
        excludeFilters = {@Filter(EnableWebMvc.class), @Filter(Controller.class)})
@PropertySource("classpath:/jdbc.properties")
public class AppConfig {
    @Autowired
    private Environment evn;

    @Profile("dbcp")
    @Bean
    public DataSource dataSource(){
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
    @Bean
    public JndiObjectFactoryBean dataSource2(){
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("jndi/mybatis");
        jndiObjectFactoryBean.setResourceRef(true);
        jndiObjectFactoryBean.setProxyInterface(DataSource.class);
        return jndiObjectFactoryBean;
    }

}
