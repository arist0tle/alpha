package com.geektcp.alpha.spring.security.auth.provider;

import com.geektcp.alpha.spring.security.exception.BaseException;
import com.geektcp.alpha.spring.security.service.UserService;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class LoginProvider implements AuthenticationProvider {

    private LoginParameters loginParameters;
    private UserService userService;

    @Autowired
    public void setAutowired(UserService userService, LoginParameters loginParameters) {
        this.userService = userService;
        this.loginParameters = loginParameters;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(loginParameters.getJwtTokenSecret()).parseClaimsJws(token);
            log.info("check authorization success!");
            return true;
        } catch (Exception ex) {
            log.error("validate failed : {}", ex.getMessage());
            return false;
        }
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        log.info("33333333323");
        UserDetails userDetails = userService.getUserDetailByUserName(authentication.getPrincipal().toString());
        if(Objects.isNull(userDetails)){
            throw new BaseException("userDetails is null!");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new LoginToken(userDetails, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
