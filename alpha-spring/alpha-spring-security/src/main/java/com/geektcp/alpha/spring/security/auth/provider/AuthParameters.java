package com.geektcp.alpha.spring.security.auth.provider;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author haiyang on 2020-04-12 17:12
 */
@Component
@Data
@PropertySource("classpath:auth.properties")
public  class AuthParameters {

    private String jwtTokenSecret;
    private long tokenExpiredMs;

    @Value("${jwtTokenSecret}")
    public void setJwtTokenSecret(String jwtTokenSecret) {
        this.jwtTokenSecret = jwtTokenSecret;
    }

    @Value("${tokenExpiredMs}")
    public void setTokenExpiredMs(long tokenExpiredMs) {
        this.tokenExpiredMs = tokenExpiredMs;
    }
}
