package com.geektcp.alpha.dubbo.provider.service;


import alpha.common.base.glog.GLog;
import alpha.common.base.glog.LogFactory;
import alpha.common.base.log.TLog;
import com.alibaba.dubbo.config.annotation.Service;
import com.geektcp.alpha.dubbo.api.DemoService;

import java.time.Instant;

@Service
public class DemoServiceImpl implements DemoService {
    private static final GLog LOG = LogFactory.getLogger(DemoServiceImpl.class);

    @Override
    public String sayHello(String name) {
        LOG.info("[" + Instant.now() + "] Hello " + name  + "by annotation");
        return "Hello " + name + " by annotation";
    }

}
