package com.geektcp.alpha.spring.security.auth.filter;

import com.geektcp.alpha.spring.security.auth.handle.AuthenticationFailHandler;
import com.geektcp.alpha.spring.security.auth.handle.AuthenticationSuccessHandler;
import com.geektcp.alpha.spring.security.exception.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author haiyang on 2020-04-12 17:30
 */
@Component
@Slf4j
public class LoginFilter extends OncePerRequestFilter {

    private AuthenticationFailHandler authenticationFailHandler;
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public void setAutowired(AuthenticationFailHandler authenticationFailHandler, AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationFailHandler = authenticationFailHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("1111111111111111LoginFilter");
        if (!StringUtils.contains(request.getRequestURI(), "api/login")) {
            chain.doFilter(request, response);
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(StringUtils.isAnyEmpty(username, password)){
            authenticationFailHandler.onAuthenticationFailure(request, response, new LoginException("登录失败，开思账户手机号为空！"));
        }
        authenticationSuccessHandler.onAuthenticationSuccess(request, response, null);
        chain.doFilter(request, response);
    }
}
