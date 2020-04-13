package com.geektcp.alpha.spring.security.auth.provider;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author haiyang on 2020-04-13 10:01
 */
public class LoginToken extends AbstractAuthenticationToken {

    private final Object principal;


    public LoginToken(Object principal){
        super(null);
        this.principal = principal;
        setAuthenticated(false);
    }

    public LoginToken(Object principal, Collection<? extends GrantedAuthority> authorities){
        super(null);
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

}
