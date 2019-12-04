package com.geektcp.alpha.agent.builder;

import com.geektcp.alpha.agent.servlet.MetricsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import static com.geektcp.alpha.agent.constant.Metrics.*;

/**
 * @author haiyang.tang on 11.27 027 16:51:37.
 */
public class JettyBuilder {

    private JettyBuilder(){
    }

    public static void build() {
        try {
            int port = 3300;
            Server server = new Server(port);
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/prometheus");
            server.setHandler(context);
            context.addServlet(new ServletHolder(new MetricsServlet()), "/metrics");
            server.start();
            System.out.println(String.format("start prometheus exporter 1.1: http://localhost:%d/prometheus/metrics", port));
            ThyCacheBuilder.init();
            server.join();
        } catch (Exception e) {
            System.out.println("JettyBuilder: " + e.getMessage());
        }
    }



}
