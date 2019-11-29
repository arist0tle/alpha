package alpha.geektcp.agent;

/**
 * @author haiyang.tang on 11.14 014 16:29:31.
 */

import alpha.geektcp.agent.builder.BuddyAgentBuilder;
import alpha.geektcp.agent.builder.JettyBuilder;

import java.lang.instrument.Instrumentation;

public class Agent {

    private Agent() {
    }

    public static void premain(String arguments, Instrumentation instrumentation) {
        System.out.println("Agent for time measure");

//        TransformerAgentBuilder.build(instrumentation);

        BuddyAgentBuilder.build(instrumentation);

        new Thread(){
            @Override
            public void run() {
                JettyBuilder.build();
            }
        }.start();

    }
}
