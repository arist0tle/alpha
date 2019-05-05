package com.geektcp.alpha.db.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by tanghaiyang on 2017/12/26.
 */
@EnableCaching
@SpringBootApplication
@ServletComponentScan

@EnableJpaRepositories("com.geektcp.alpha")
@EntityScan("com.geektcp.alpha")
@ComponentScan("com.geektcp.alpha")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
