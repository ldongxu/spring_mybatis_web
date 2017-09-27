package com.ldongxu.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

/**
 * 添加Servlet、Listener和Filter
 * <p>
 * Created by 刘东旭 on 2017/8/17.
 */
public class WebXmlInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
        filter.addMappingForUrlPatterns(null, false, "/*");
        filter.setInitParameter("encoding", "UTF-8");
        filter.setInitParameter("forceEncoding", "true");

        FilterRegistration.Dynamic shiroFilter = servletContext.addFilter("shiroFilter", DelegatingFilterProxy.class);
        shiroFilter.setInitParameter("targetFilterLifecycle","true");
        shiroFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),false,"/admin/*");
    }
}
