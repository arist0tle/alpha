package com.geektcp.alpha.spring.security.auth.handle;

import com.geektcp.alpha.spring.security.auth.provider.LoginProvider;
import com.geektcp.alpha.spring.security.domain.vo.JwtVo;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
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
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info("User: Login successfully.");
        this.returnJson(response,authentication);
    }

    private void returnJson(HttpServletResponse response,Authentication authentication) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        JwtVo vo = new JwtVo();
        vo.setTokenType("Bearer");
        vo.setToken(tokenProvider.createJwtToken(authentication));
        responseWriter(response,vo);
    }

    private void responseWriter(HttpServletResponse response, JwtVo vo) {
        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(vo.toString().getBytes());
            bufferedOutputStream.close();
        } catch (IOException e) {
            log.error(Throwables.getStackTraceAsString(e));
        }
    }

}
