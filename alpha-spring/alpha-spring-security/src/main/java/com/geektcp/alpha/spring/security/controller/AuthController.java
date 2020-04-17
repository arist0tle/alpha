package com.geektcp.alpha.spring.security.controller;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.geektcp.alpha.spring.security.annotation.AnonymousAccess;
import com.geektcp.alpha.spring.security.auth.SecurityProperties;
import com.geektcp.alpha.spring.security.auth.handle.SuccessHandler;
import com.geektcp.alpha.spring.security.auth.provider.LoginProvider;
import com.geektcp.alpha.spring.security.auth.provider.TokenProvider;
import com.geektcp.alpha.spring.security.domain.qo.AuthUser;
import com.geektcp.alpha.spring.security.domain.vo.UserVo;
import com.geektcp.alpha.spring.security.exception.BaseException;
import com.geektcp.alpha.spring.security.service.UserService;
import com.geektcp.alpha.spring.security.util.EncryptUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private SuccessHandler authenticationSuccessHandler;

    @Autowired
    public AuthController(TokenProvider tokenProvider,
                          SecurityProperties properties,
                          LoginProvider loginProvider,
                          SuccessHandler authenticationSuccessHandler) {
        this.tokenProvider = tokenProvider;
        this.loginProvider = loginProvider;
        this.properties = properties;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @ApiOperation("登录授权")
    @AnonymousAccess
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUser authUser, HttpServletRequest request) {
        String password;
        try {
            RSA rsa = EncryptUtils.getRsa();
            byte[] bytesPassword = rsa.decrypt(authUser.getEncryptPassword(), KeyType.PrivateKey);
            password = StringUtils.toEncodedString(bytesPassword, CharsetUtil.CHARSET_UTF_8);
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
//            String token = authenticationSuccessHandler.createJwtToken(authentication);
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
