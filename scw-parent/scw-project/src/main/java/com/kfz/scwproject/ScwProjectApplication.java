package com.kfz.scwproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableEurekaClient
@MapperScan(basePackages = {"com.kfz.scwproject.dao"})
@SpringBootApplication
public class ScwProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScwProjectApplication.class, args);
    }

}
