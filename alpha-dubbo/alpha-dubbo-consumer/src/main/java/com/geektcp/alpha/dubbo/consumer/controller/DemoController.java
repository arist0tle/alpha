package com.geektcp.alpha.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.geektcp.alpha.dubbo.api.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Reference
    private DemoService demoService;

    @GetMapping("sayHello")
    public String sayHello(){
        return demoService.sayHello("Dubbo");
    }
}
