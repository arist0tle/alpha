package com.geektcp.alpha.spring.security.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {

    private AuthParameters authParameters;

    @Autowired
    public JwtTokenProvider(AuthParameters authParameters){
        this.authParameters = authParameters;
    }

    public String createJwtToken(Authentication authentication) {
        User userObject = (User)authentication.getPrincipal();
        String username = userObject.getUsername();
        Date expireTime = new Date(System.currentTimeMillis() + authParameters.getTokenExpiredMs());
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(expireTime)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, authParameters.getJwtTokenSecret())
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
            Jwts.parser().setSigningKey(authParameters.getJwtTokenSecret()).parseClaimsJws(token);
            log.info("check authorization success!");
            return true;
        } catch (Exception ex) {
            log.error("validate failed : {}", ex.getMessage());
            return false;
        }
    }

    @Component
    @PropertySource("classpath:auth.properties")
    public static class AuthParameters {

        private String jwtTokenSecret;
        private long tokenExpiredMs;

         String getJwtTokenSecret() {
            return jwtTokenSecret;
        }

        @Value("${jwtTokenSecret}")
        public void setJwtTokenSecret(String jwtTokenSecret) {
            this.jwtTokenSecret = jwtTokenSecret;
        }

         long getTokenExpiredMs() {
            return tokenExpiredMs;
        }

        @Value("${tokenExpiredMs}")
        public void setTokenExpiredMs(long tokenExpiredMs) {
            this.tokenExpiredMs = tokenExpiredMs;
        }
    }
}
