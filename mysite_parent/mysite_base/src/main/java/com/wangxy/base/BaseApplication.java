package com.wangxy.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

@SpringBootApplication
@MapperScan("com.wangxy.base.mapper")
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class,args);
    }
    @Bean
    public IdWorker idWorkerGen(){
        return new IdWorker(1,1);
    }
}
