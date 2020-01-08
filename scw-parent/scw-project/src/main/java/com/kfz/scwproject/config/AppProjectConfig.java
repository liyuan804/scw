package com.kfz.scwproject.config;

import com.kfz.scwproject.service.OSSTemplate;
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
