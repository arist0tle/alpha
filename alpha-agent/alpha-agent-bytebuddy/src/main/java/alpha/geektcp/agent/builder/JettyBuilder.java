package alpha.geektcp.agent.builder;

import alpha.geektcp.agent.builder.servlet.HelloServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * @author haiyang.tang on 11.27 027 16:51:37.
 */
public class JettyBuilder {



    public static void build() {
        try {
            Server server = new Server(33000);
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/metric");
            server.setHandler(context);
            context.addServlet(new ServletHolder(new HelloServlet()), "/*");
            server.start();
            server.join();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}
