package com.pipi.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class FileUploadConfig {

	// @Bean
	// public MultipartConfigElement multipartConfigElement() {
	// MultipartConfigFactory factory = new MultipartConfigFactory();
	// // 文件最大
	// factory.setMaxFileSize("10240KB"); // KB,MB
	// /// 设置总上传数据总大小
	// factory.setMaxRequestSize("102400KB");
	// return factory.createMultipartConfig();
	// }

	@Bean
	public CommonsMultipartResolver getmultipartConfig() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(5242880);
		return multipartResolver;
	}

}