package com.geektcp.alpha.spring.security.auth.handle;

import com.geektcp.alpha.spring.security.auth.provider.LoginProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Will run when user login username and pass word validate successfully.
 * There will generate a token return to response.
 */
@Service("authenticationSuccessHandler")
@Slf4j
public class SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private LoginProvider tokenProvider;

    @Autowired
    public SuccessHandler(LoginProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        log.info("User: Login successfully.");
        this.returnJson(response,authentication);
    }

    private void returnJson(HttpServletResponse response,Authentication authentication) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println("{\"tokenType\":\"Bearer\",\"token\": \"" + tokenProvider.createJwtToken(authentication) + "\"}");
    }
}
