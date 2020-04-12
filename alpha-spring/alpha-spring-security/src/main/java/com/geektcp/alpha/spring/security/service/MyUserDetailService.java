package com.geektcp.alpha.spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * This service will reload user information from database.
 */
@Service("myUserDetailService")
public class MyUserDetailService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public MyUserDetailService(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return this.userService.getUserDetailByUserName(username);
    }
}
