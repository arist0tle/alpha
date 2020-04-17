package com.geektcp.alpha.spring.security.controller;

import com.geektcp.alpha.spring.security.annotation.AnonymousAccess;
import com.geektcp.alpha.spring.security.auth.SecurityProperties;
import com.geektcp.alpha.spring.security.auth.provider.LoginProvider;
import com.geektcp.alpha.spring.security.auth.provider.TokenProvider;
import com.geektcp.alpha.spring.security.domain.qo.AuthUser;
import com.geektcp.alpha.spring.security.exception.BaseException;
import com.geektcp.alpha.spring.security.util.EncryptUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private TokenProvider tokenProvider;
    private SecurityProperties properties;
    private LoginProvider loginProvider;

    @Autowired
    public AuthController(TokenProvider tokenProvider,
                          SecurityProperties properties,
                          LoginProvider loginProvider) {
        this.tokenProvider = tokenProvider;
        this.loginProvider = loginProvider;
        this.properties = properties;
    }

    @ApiOperation("登录授权")
    @AnonymousAccess
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUser authUser, HttpServletRequest request) {
        String password;
        try {
            password = EncryptUtils.decrypt(authUser.getEncryptPassword());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BaseException("密码解析错误!");
        }
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    authUser.getUsername(), password);
            Authentication authentication = loginProvider.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenProvider.createToken(authentication);
            String username = authentication.getPrincipal().toString();
            Map<String, Object> authInfo = new HashMap<>();
            authInfo.put("token", properties.getTokenStartWith() + token);
            authInfo.put("username", username);
            return ResponseEntity.ok(authInfo);
        }catch (Exception e){
            throw new BaseException(e.getMessage());
        }
    }

}
