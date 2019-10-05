package com.geektcp.alpha.dubbo.producer.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.geektcp.alpha.dubbo.api.DemoService;

import java.time.Instant;

@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        System.out.println("[" + Instant.now() + "] Hello " + name  + "by annotation");
        return "Hello " + name + " by annotation";
    }

}
