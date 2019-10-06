package com.geektcp.alpha.dubbo.consumer.controller;

import alpha.common.base.log.LogFactory;
import alpha.common.base.log.TLog;
import com.alibaba.dubbo.config.annotation.Reference;
import com.geektcp.alpha.dubbo.api.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private static TLog log = LogFactory.getLogger(DemoController.class);

    @Reference
    private DemoService demoService;

    @GetMapping("sayHello")
    public String sayHello(){
        log.info("this controller: sayHello");
        return demoService.sayHello("Dubbo");
    }
}
