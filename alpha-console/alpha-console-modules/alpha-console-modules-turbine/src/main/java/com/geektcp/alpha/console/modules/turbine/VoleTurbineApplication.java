package com.geektcp.alpha.console.modules.turbine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * 访问：http://localhost:8600/hystrix
 * 监测加入：http://localhost:8600/turbine.stream
 */
@Slf4j
@SpringBootApplication
@EnableHystrixDashboard
@EnableTurbine    // 开启turbine
public class AlphaTurbineApplication {

    public static void main(String[] args) {
        log.debug("AlphaTurbineApplication startup main");
        SpringApplication application = new SpringApplication(AlphaTurbineApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}
