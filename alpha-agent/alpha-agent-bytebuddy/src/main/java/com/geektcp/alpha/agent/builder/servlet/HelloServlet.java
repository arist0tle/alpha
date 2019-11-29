package com.geektcp.alpha.agent.builder.servlet;

import com.geektcp.alpha.agent.repository.CacheRepository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author haiyang.tang on 11.27 027 18:23:54.
 */
public class HelloServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String GREETING = "# agent jetty server!";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/plain;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(GREETING);
            List<String> metricList = CacheRepository.listCache();
            for(String metric: metricList){
                response.getWriter().println(metric);
            }

            CacheRepository.clear();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}