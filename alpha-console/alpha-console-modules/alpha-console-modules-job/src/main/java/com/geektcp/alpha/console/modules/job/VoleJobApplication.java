package com.geektcp.alpha.console.modules.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AlphaJobApplication {

    public static void main(String[] args) {

        SpringApplication.run(AlphaJobApplication.class, args);
    }
}
