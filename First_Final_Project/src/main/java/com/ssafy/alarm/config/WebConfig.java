package com.ssafy.alarm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.alarm.interceptor.AdminPageInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//	// 인터셉터 설정
//	@Autowired
//	AdminPageInterceptor adminPageInterceptor;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(adminPageInterceptor).addPathPatterns("/users");
//	}
}
