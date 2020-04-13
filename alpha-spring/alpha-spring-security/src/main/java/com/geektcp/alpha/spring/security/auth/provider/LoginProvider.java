package com.geektcp.alpha.spring.security.auth.provider;

import com.geektcp.alpha.spring.security.exception.LoginException;
import com.geektcp.alpha.spring.security.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class LoginProvider implements AuthenticationProvider {

    private LoginParameters loginParameters;
    private UserService userService;

    @Autowired
    public void setAutowired(LoginParameters loginParameters){
        this.loginParameters = loginParameters;
    }

    public String createJwtToken(Authentication authentication) {
        User userObject = (User)authentication.getPrincipal();
        String username = userObject.getUsername();
        Date expireTime = new Date(System.currentTimeMillis() + loginParameters.getTokenExpiredMs());
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(expireTime)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, loginParameters.getJwtTokenSecret())
                .compact();
        log.info("token: {}", token);
        return token;
    }

    /**
     * validate token eligible.
     * if Jwts can parse the token string and no throw any exception, then the token is eligible.
     *
     * @param token a jws string.
     */
    public boolean validateToken(String token) {
        try {
            log.info("check authorization");
            Jwts.parser().setSigningKey(loginParameters.getJwtTokenSecret()).parseClaimsJws(token);
            log.info("check authorization success!");
            return true;
        } catch (Exception ex) {
            log.error("validate failed : {}", ex.getMessage());
            return false;
        }
    }

    @Override
    public Authentication authenticate(Authentication authentication)  {
        log.info("33333333323");
        UserDetails userDetails;
        try {
            userDetails = userService.getUserDetailByUserName(authentication.getPrincipal().toString());
        } catch (Exception e) {
            throw new LoginException(e.getMessage());
        }
        return new LoginToken(userDetails,userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
