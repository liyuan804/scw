package com.kfz.scwuser.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan(basePackages = {"com.kfz.scwuser.controller"})
@Configuration
public class AppSwaggerConfig {

}
