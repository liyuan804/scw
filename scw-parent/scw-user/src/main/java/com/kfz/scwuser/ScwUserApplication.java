package com.kfz.scwuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@EnableEurekaClient
@MapperScan(basePackages = {"com.kfz.scwuser.dao"})
@SpringBootApplication
public class ScwUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScwUserApplication.class, args);
    }

}
