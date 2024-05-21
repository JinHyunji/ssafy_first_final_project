package com.ssafy.alarm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.alarm.model.dao")
public class DBConfig {

}
