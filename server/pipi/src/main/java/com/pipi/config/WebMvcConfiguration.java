package com.pipi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.pipi.intercepter.LoginInterceptor;

//@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private LoginInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		// 映射为 user 的控制器下的所有映射
		registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/toLogin", "/address/**",
				"/cart/**", "/disk/getMenuDisk", "/disk/getDiskById", "/evaluate/getEvalsByDiskId", "/menu/getAllMenu",
				"/order/saveOrder", "/order/changeStatus", "/order/getAllOrder", "/order/getOrderDetailByOrderId",
				"/order/getDisksByOrderId", "/shop/getInfo", "/pic/upload", "/evaluate/saveEvals"
				,"/swagger**/**","/v2/api-docs","/swagger-resources/configuration/ui");
		super.addInterceptors(registry);
	}

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//	}

	// @Override
	// public void addViewControllers(ViewControllerRegistry registry) {
	// registry.addViewController("/").setViewName("login");
	// super.addViewControllers(registry);
	// }

}