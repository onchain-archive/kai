/**  
 * Title: UanConfiguration.java
 * Description: UanConfiguration
 * Copyright Agriculture Bank of China
 * @author Bo Liu
 * @date 2018-09-20
 * @version 1.0
 */ 
package com.abc.common.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.abc.common.servlet.NodeDeploymentDescriptorListener;
import com.abc.common.servlet.ThreadContextCleanerFilter;
import com.abc.common.springmvc.RequestInterceptor;

/**
 * Title: UanConfiguration
 * @Description: UanConfiguration
 * @author Bo Liu
 * @date 2018-09-20
 */
@Configuration
@ComponentScan(basePackages = "com.abc.*")
@MapperScan("com.abc")
public class UanConfiguration extends WebMvcConfigurerAdapter {
	@Bean
	public RequestInterceptor requestInterceptor() {
		return new RequestInterceptor();
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestInterceptor());
		super.addInterceptors(registry);
	}

	/** 
	 * @Description: dispatcherServletRegistrationBean
	 * @param dispatcherServlet
	 * @return  ServletRegistrationBean
	 * @throws 
	 */ 
	@Bean
	public ServletRegistrationBean dispatcherServletRegistrationBean(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean bean = new ServletRegistrationBean(dispatcherServlet);
		bean.getUrlMappings().clear();
		bean.addUrlMappings("/*");
		bean.setLoadOnStartup(1);
		return bean;
	}

	/** 
	 * @Description: nodeDeploymentDescriptorListener
	 * @return  ServletListenerRegistrationBean
	 * @throws 
	 */ 
	@Bean
	public ServletListenerRegistrationBean nodeDeploymentDescriptorListener() {
		ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();
		bean.setListener(new NodeDeploymentDescriptorListener());
		return bean;
	}

	/** 
	 * @Description: threadContextCleanerFilter
	 * @return  FilterRegistrationBean
	 * @throws 
	 */ 
	@Bean
	@Order(998)
	public FilterRegistrationBean threadContextCleanerFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new ThreadContextCleanerFilter());
		bean.addUrlPatterns("/*");
		return bean;
	}

	/** (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#configureDefaultServletHandling(org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer)
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/** (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/hello");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}

}
