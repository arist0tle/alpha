package com.geektcp.alpha.agent.builder;

import com.geektcp.alpha.agent.builder.servlet.HelloServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * @author haiyang.tang on 11.27 027 16:51:37.
 */
public class JettyBuilder {

    private JettyBuilder(){
    }

    public static void build() {
        try {
            Server server = new Server(33000);
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/metrics");
            server.setHandler(context);
            context.addServlet(new ServletHolder(new HelloServlet()), "/*");
            server.start();
//            String host = InetAddress.getLocalHost().getHostAddress();
            System.out.println(String.format("start jetty server | + http://%s:%d/metrics", "localhost", 33000));
            server.join();
        } catch (Exception e) {
            System.out.println("JettyBuilder: " + e.getMessage());
        }
    }

}
