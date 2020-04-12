package com.geektcp.alpha.spring.security.auth;

import com.geektcp.alpha.spring.security.auth.filter.JwtAuthenticationFilter;
import com.geektcp.alpha.spring.security.auth.handle.AuthenticationFailHandler;
import com.geektcp.alpha.spring.security.auth.handle.AuthenticationSuccessHandler;
import com.geektcp.alpha.spring.security.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter{

    private MyUserDetailService myUserDetailService;

    private AuthenticationSuccessHandler successHandler;

    private AuthenticationFailHandler failHandler;

    private AuthenticationEntryPoint entryPoint;

    @Autowired
    public MySecurityConfig(MyUserDetailService myUserDetailService,
                            AuthenticationSuccessHandler successHandler,
                            AuthenticationFailHandler failHandler,
                            AuthenticationEntryPoint entryPoint
                             ){
        this.myUserDetailService = myUserDetailService;
        this.successHandler = successHandler;
        this.failHandler = failHandler;
        this.entryPoint = entryPoint;
    }

    @Bean
    public JwtAuthenticationFilter getJwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(getJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/v2/api-docs/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginProcessingUrl("/api/login")
                .successHandler(successHandler)
                .failureHandler(failHandler)
                .and().exceptionHandling().authenticationEntryPoint(entryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }
}
