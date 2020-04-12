package com.geektcp.alpha.spring.security.controller;

import com.geektcp.alpha.spring.security.service.UserService;
import com.geektcp.alpha.spring.security.domain.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tanghaiyang
 * 22:56 2018/9/2
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    @PreAuthorize("hasAuthority('admin')")
    public UserVo getUserByName(@RequestParam("username") String userName) {
        log.info("success!");
        return userService.getUserByUserName(userName);
    }
}
