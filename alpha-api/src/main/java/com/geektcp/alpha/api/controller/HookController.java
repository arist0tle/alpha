package com.geektcp.alpha.api.controller;


import alpha.common.base.constant.Constants;
import com.geektcp.alpha.common.spring.model.TResponse;
import com.geektcp.alpha.sys.auth.constant.AuthStatus;
import com.geektcp.alpha.sys.auth.service.SysUserService;
import com.geektcp.alpha.sys.auth.shiro.model.LoginQo;
import com.geektcp.alpha.sys.auth.shiro.model.UserRoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author tanghaiyang on 2018/05/22.
 */
@Slf4j
@Api(description = "prometheus web hook")
@RestController
@RequestMapping("/")
public class HookController {

    @RequestMapping("/hook")
    public String hook(@RequestBody String body) {
        //处理预警信息(邮件、短信、钉钉)
        log.info("web hook警报系统,body:\n{}",body);
        return "success";
    }
}
