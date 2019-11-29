package alpha.geektcp.agent.builder;

import alpha.geektcp.agent.transformer.TheClassFileTransformer;

import java.lang.instrument.Instrumentation;

/**
 * @author haiyang.tang on 11.27 027 10:11:14.
 */
public class TransformerAgentBuilder {

    public static void build(Instrumentation instrumentation){
        instrumentation.addTransformer(new TheClassFileTransformer());
    }

}
