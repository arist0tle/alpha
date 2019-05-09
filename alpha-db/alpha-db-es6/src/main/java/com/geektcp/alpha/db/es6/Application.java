package com.geektcp.alpha.db.es6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by tanghaiyang on 2018/1/4.
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
@EntityScan({"com.haizhi.graph"})
@ComponentScan({"com.haizhi.graph"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
