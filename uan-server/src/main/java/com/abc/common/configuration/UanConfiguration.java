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

@Configuration
@ComponentScan(basePackages = "com.abc.*")
@MapperScan("com.abc")
public class UanConfiguration extends WebMvcConfigurerAdapter {
	@Bean
	public RequestInterceptor requestInterceptor() {
		return new RequestInterceptor();
	}

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestInterceptor());
		super.addInterceptors(registry);
	}

	@Bean
	public ServletRegistrationBean dispatcherServletRegistrationBean(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean bean = new ServletRegistrationBean(dispatcherServlet);
		bean.getUrlMappings().clear();
		bean.addUrlMappings("/*");
		bean.setLoadOnStartup(1);
		return bean;
	}

	@Bean
	public ServletListenerRegistrationBean nodeDeploymentDescriptorListener() {
		ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();
		bean.setListener(new NodeDeploymentDescriptorListener());
		return bean;
	}

	@Bean
	@Order(998)
	public FilterRegistrationBean threadContextCleanerFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new ThreadContextCleanerFilter());
		bean.addUrlPatterns("/*");
		return bean;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/hello");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}

}
