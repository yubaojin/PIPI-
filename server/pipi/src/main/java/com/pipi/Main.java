package com.pipi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.pipi.config.PipiApplication;

/**
 * 程序运行类
 * 
 * @author yubaojin
 *
 */
public class Main extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(PipiApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 设置启动类，用于独立的Tomcat入口
		// 应为springboot没有web.xml文件
		return builder.sources(PipiApplication.class);
	}
}
