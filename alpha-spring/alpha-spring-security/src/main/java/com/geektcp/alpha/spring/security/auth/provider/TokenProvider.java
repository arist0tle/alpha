package com.geektcp.alpha.spring.security.auth.provider;

import com.geektcp.alpha.spring.security.auth.SecurityProperties;
import com.geektcp.alpha.spring.security.util.EncryptUtils;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author /
 */
@Slf4j
@Component
public class TokenProvider {

    private SecurityProperties properties;
    private static final String AUTHORITIES_KEY = "auth";
    private LoginParameters loginParameters;

    @Autowired
    public void setAutowired(SecurityProperties properties, LoginParameters loginParameters) {
        this.loginParameters = loginParameters;
        this.properties = properties;
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Date expireTime = new Date(System.currentTimeMillis() + loginParameters.getExpiration());
        String username = authentication.getName();
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(expireTime)
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(EncryptUtils.buildKey(loginParameters.getEncryptSecret()), SignatureAlgorithm.HS512)
                .compact();
        log.info("token: {}", token);
        return token;
    }

    ////////////////////////////////////////////////////////////
    private Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(loginParameters.getEncryptSecret())
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    private boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(loginParameters.getEncryptSecret()).parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid: {}", e.getMessage());
        }
        return false;
    }

    private String getToken(HttpServletRequest request) {
        final String requestHeader = request.getHeader(properties.getHeader());
        if (requestHeader != null && requestHeader.startsWith(properties.getTokenStartWith())) {
            return requestHeader.substring(7);
        }
        return null;
    }
}
