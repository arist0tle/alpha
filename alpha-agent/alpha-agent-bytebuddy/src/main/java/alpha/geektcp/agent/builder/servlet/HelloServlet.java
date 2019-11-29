package alpha.geektcp.agent.builder.servlet;

import alpha.geektcp.agent.repository.CacheRepository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author haiyang.tang on 11.27 027 18:23:54.
 */
public class HelloServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String GREETING = "# agent jetty server!";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/plain");
            response.setStatus(HttpServletResponse.SC_OK);
            StringBuilder sb = new StringBuilder();
            sb.append(GREETING).append("<br>");
            sb.append(CacheRepository.get("metric")).append("<br>");
            response.getWriter().println(sb.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}