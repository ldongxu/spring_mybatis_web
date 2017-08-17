package com.ldongxu.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 添加Servlet、Listener和Filter
 * <p>
 * Created by 刘东旭 on 2017/8/17.
 */
public class MyServletInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
       FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
        filter.addMappingForUrlPatterns(null,false,"/*");
        filter.setInitParameter("encoding","UTF-8");
        filter.setInitParameter("forceEncoding","true");
    }
}
