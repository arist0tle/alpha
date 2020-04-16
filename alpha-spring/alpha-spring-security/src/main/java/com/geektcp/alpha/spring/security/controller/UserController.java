package com.geektcp.alpha.spring.security.controller;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.geektcp.alpha.spring.security.annotation.AnonymousAccess;
import com.geektcp.alpha.spring.security.auth.SecurityProperties;
import com.geektcp.alpha.spring.security.auth.provider.LoginProvider;
import com.geektcp.alpha.spring.security.auth.provider.TokenProvider;
import com.geektcp.alpha.spring.security.domain.qo.AuthUser;
import com.geektcp.alpha.spring.security.domain.vo.JwtUser;
import com.geektcp.alpha.spring.security.exception.BaseException;
import com.geektcp.alpha.spring.security.service.UserService;
import com.geektcp.alpha.spring.security.domain.vo.UserVo;
import com.geektcp.alpha.spring.security.util.EncryptUtils;
import com.geektcp.alpha.spring.security.util.RedisUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tanghaiyang
 * 22:56 2018/9/2
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {


    private UserService userService;
    private RedisUtils redisUtils;
    private TokenProvider tokenProvider;
    private SecurityProperties properties;
    private LoginProvider loginProvider;
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    public UserController(UserService userService,
                          RedisUtils redisUtils,
                          TokenProvider tokenProvider,
                          SecurityProperties properties,
                          LoginProvider loginProvider,
                          AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.userService = userService;
        this.redisUtils = redisUtils;
        this.tokenProvider = tokenProvider;
        this.loginProvider = loginProvider;
        this.properties = properties;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @GetMapping(value = "/user")
    @PreAuthorize("hasAuthority('admin')")
    public UserVo getUserByName(@RequestParam("username") String userName) {
        log.info("success!");
        return userService.getUserByUserName(userName);
    }


    @ApiOperation("登录授权")
    @AnonymousAccess
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUser authUser, HttpServletRequest request) {
        RSA rsa = EncryptUtils.getRsa();
        String password = new String(rsa.decrypt(authUser.getPassword(), KeyType.PrivateKey));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authUser.getUsername(), password);
        Authentication authentication = loginProvider.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        Map<String, Object> authInfo = new HashMap<>();
        authInfo.put("token", properties.getTokenStartWith() + token);
        authInfo.put("user", jwtUser);
        return ResponseEntity.ok(authInfo);
    }

}
