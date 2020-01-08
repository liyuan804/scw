package com.kfz.scwproject1.config;


import com.kfz.scwproject1.service.OSSTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppProjectConfig {

    @ConfigurationProperties(prefix="oss")
    @Bean
    public OSSTemplate ossTemplate(){
        return new OSSTemplate();
    }
}
