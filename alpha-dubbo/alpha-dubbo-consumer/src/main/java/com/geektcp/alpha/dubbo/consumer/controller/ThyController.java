package com.geektcp.alpha.dubbo.consumer.controller;

import alpha.common.base.log.LogFactory;
import alpha.common.base.log.TLog;
import com.alibaba.dubbo.config.annotation.Reference;
import com.geektcp.alpha.dubbo.api.ThyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThyController {
    private static TLog log = LogFactory.getLogger(ThyController.class);

    @Reference
    private ThyService thyService;

    @GetMapping("sayHello")
    public String sayHello(){
        log.info("this controller: sayHello");
        return thyService.sayHello("Dubbo");
    }
}
