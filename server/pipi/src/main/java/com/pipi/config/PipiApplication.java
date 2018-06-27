package com.pipi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 主配置
 * 
 * @author yubaojin
 *
 */
@Configuration
@ComponentScan(basePackages = "com.pipi")
@EnableTransactionManagement
@MapperScan(basePackages="com.pipi.dao.mapper")
@SpringBootApplication
public class PipiApplication {
	
	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean reg = new ServletRegistrationBean(dispatcherServlet);
		reg.getUrlMappings().clear();
		reg.addUrlMappings("/");
		return reg;
	}
	
}
