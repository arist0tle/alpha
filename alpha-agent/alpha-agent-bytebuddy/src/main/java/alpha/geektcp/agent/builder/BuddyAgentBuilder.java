package alpha.geektcp.agent.builder;

import alpha.geektcp.agent.advice.GetMappingAdvisor;
import alpha.geektcp.agent.advice.PostMappingAdvisor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.nameContains;
import static net.bytebuddy.matcher.ElementMatchers.nameStartsWith;
import static net.bytebuddy.matcher.ElementMatchers.not;


/**
 * @author haiyang.tang on 11.27 027 9:57:59.
 */
public class BuddyAgentBuilder {


    /*
    * invalid:
    *  AgentBuilder agentBuilder = new AgentBuilder.Default();
    *  agentBuilder.with(eager).type(ElementMatchers.nameStartsWith("com.geektcp")).transform(transformer);
    *
    *  invalid:
    *  AgentBuilder agentBuilder = new AgentBuilder.Default().with(eager);
    *  agentBuilder.type(ElementMatchers.nameStartsWith("com.geektcp")).transform(transformer);
    * */
    public static void build(Instrumentation instrumentation) {

        ElementMatcher.Junction<TypeDescription> ignore = nameStartsWith("com.sun.")
                .or(nameStartsWith("com.sun."))
                .or(nameStartsWith("sun."))
                .or(nameStartsWith("jdk."))
                .or(nameStartsWith("org.aspectj."))
                .or(nameStartsWith("org.groovy."))
                .or(nameStartsWith("com.p6spy."))
                .or(nameStartsWith("net.bytebuddy."))
                .or(nameStartsWith("org.springframework"))
                .or(nameStartsWith("org.slf4j.").and(not(nameStartsWith("org.slf4j.impl."))))
                .or(nameContains("javassist"))
                .or(nameContains(".asm.")
                );

        ElementMatcher.Junction<TypeDescription> filter = nameStartsWith("com.geektcp");

        AgentBuilder.InitializationStrategy.SelfInjection.Eager eager = new AgentBuilder.InitializationStrategy.SelfInjection.Eager();
        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, module) ->
                builder
//                        .method(ElementMatchers.any())
//                        .intercept(Advice.to(MappingAdvisor.class));
                        .method(ElementMatchers.declaresAnnotation(ElementMatchers.annotationType(GetMapping.class)))
                        .intercept(Advice.to(GetMappingAdvisor.class))
                        .method(ElementMatchers.declaresAnnotation(ElementMatchers.annotationType(PostMapping.class)))
                        .intercept(Advice.to(PostMappingAdvisor.class));
//                        .method(ElementMatchers.any())
//                        .intercept(Advice.to(TimerAdvice.class));

        AgentBuilder agentBuilder = new AgentBuilder.Default().with(eager).ignore(ignore).type(filter).transform(transformer);

        agentBuilder.installOn(instrumentation);
    }

}
