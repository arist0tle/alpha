package com.geektcp.alpha.dubbo.consumer;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@DubboComponentScan(basePackages = "com.learnDubbo.demo.consumer.controller")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
