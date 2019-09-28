package com.geektcp.alpha.api.controller;

import alpha.common.base.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanghaiyang on 2019/3/4.
 */
@Api(description = "[权限测试]")
@RestController
@RequestMapping("/test")
public class TestController {

    @RequiresAuthentication
    @ApiOperation(value = "test0", notes = "权限测试RequiresAuthentication")
    @RequestMapping(value = "test0", method = RequestMethod.GET)
    public Response test0() {
        return Response.success("000000");
    }

    @RequiresUser
    @ApiOperation(value = "test1", notes = "权限测试RequiresUser")
    @RequestMapping(value = "test1", method = RequestMethod.GET)
    public Response test1() {
        return Response.success("111111111");
    }

    @RequiresGuest
    @ApiOperation(value = "test2", notes = "权限测试RequiresGuest")
    @RequestMapping(value = "test2", method = RequestMethod.GET)
    public Response test2() {
        return Response.success("22222");
    }

    @RequiresRoles("admin")
    @ApiOperation(value = "test3", notes = "权限测试RequiresRoles")
    @RequestMapping(value = "test3", method = RequestMethod.GET)
    public Response test3() {
        return Response.success("3333333");
    }

    @RequiresPermissions("admin:/api/xxx")
    @ApiOperation(value = "test4", notes = "权限测试RequiresPermissions")
    @RequestMapping(value = "test4", method = RequestMethod.GET)
    public Response test4() {
        return Response.success("4444444");
    }

    @RequiresPermissions("user:/api/xxx")
    @ApiOperation(value = "test5", notes = "权限测试RequiresPermissions")
    @RequestMapping(value = "test5", method = RequestMethod.GET)
    public Response test5() {
        return Response.success("55555");
    }

}
