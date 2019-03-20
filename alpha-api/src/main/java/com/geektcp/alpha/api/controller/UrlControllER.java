package com.geektcp.alpha.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tanghaiyang on 2019/3/20.
 */
@Slf4j
@RestController
@RequestMapping("/url")
@Api(description = "resttemplate请求时转码测试")
public class UrlController {

    @ApiOperation(value = "测试接口", notes = "{\"addd\":\"datastr\"}")
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test(String gdbQuery){
        log.info("gdbQuery: {}", gdbQuery);

        log.info("=======================");
        return gdbQuery;
    }
}
