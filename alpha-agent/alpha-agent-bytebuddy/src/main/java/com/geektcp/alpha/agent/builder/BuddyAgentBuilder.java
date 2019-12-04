package com.geektcp.alpha.agent.builder;

import com.geektcp.alpha.agent.advice.*;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.nameContains;
import static net.bytebuddy.matcher.ElementMatchers.nameStartsWith;


/**
 * @author haiyang.tang on 11.27 027 9:57:59.
 */
public class BuddyAgentBuilder {

    private BuddyAgentBuilder() {
    }

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
                .or(nameStartsWith("org.slf4j"))
                .or(nameStartsWith("org.apache.logging.log4j"))
                .or(nameStartsWith("com.casstime.job.core.rpc"))
                .or(nameContains("slf4j"))
                .or(nameContains("log4j"))
                .or(nameContains("javassist"))
                .or(nameContains(".asm.")
                );

//        ElementMatcher.Junction<TypeDescription> filter = nameStartsWith("com.casstime");

        ElementMatcher.Junction<TypeDescription> filter = nameStartsWith("com.casstime");
//                .and(ElementMatchers.declaresAnnotation(ElementMatchers.annotationType(RestController.class)));

        AgentBuilder.InitializationStrategy.SelfInjection.Eager eager = new AgentBuilder.InitializationStrategy.SelfInjection.Eager();

//        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, module) ->
//                builder.method(ElementMatchers.declaresAnnotation(ElementMatchers.annotationType(PostMapping.class)))
//                        .intercept(Advice.to(PostMappingAdvisor.class))
//                        .method(ElementMatchers.declaresAnnotation(ElementMatchers.annotationType(GetMapping.class)))
//                        .intercept(Advice.to(GetMappingAdvisor.class))
//                        .method(ElementMatchers.declaresAnnotation(ElementMatchers.annotationType(RequestMapping.class)))
//                        .intercept(Advice.to(RequestMappingAdvisor.class));

//                        .method(ElementMatchers.declaresAnnotation(ElementMatchers.annotationType(PostMapping.class)))
//                        .intercept(ExceptionMethod.throwing(RuntimeException.class, "agent exception"));

//        ElementMatcher.Junction elementMatchers = ElementMatchers.declaresAnnotation(
//                ElementMatchers.annotationType(org.springframework.web.bind.annotation.GetMapping.class)
//        );
//        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, module) ->
//                builder.method(ElementMatchers.any())
//                        .intercept(Advice.to(GetMappingAdvisor.class));

        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, module) ->
                builder.method(ElementMatchers.isAnnotatedWith(ElementMatchers.isAnnotation()))
                        .intercept(Advice.to(MappingAdvisor.class));

//        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, module) ->
//                builder.method(ElementMatchers.any())
//                        .intercept(Advice.to(MappingAdvisor.class));

//        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, module) ->
//                builder.method(ElementMatchers.declaresAnnotation(ElementMatchers.annotationType(PostMapping.class)))
//                        .intercept(Advice.to(PostMappingAdvisor.class));
//
//        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, module) ->
//                builder.method(ElementMatchers.any())
//                        .intercept(Advice.to(TimerAdvice.class));
////
//        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, module) ->
//                builder.method(ElementMatchers.declaresAnnotation(ElementMatchers.annotationType(ApiOperation.class)))
//                        .intercept(Advice.to(ApiOperationAdvisor.class));
//
//        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, module) ->
//                builder.method(ElementMatchers.declaresAnnotation(ElementMatchers.annotationType(RequestMapping.class)))
//                        .intercept(Advice.to(RequestMappingAdvisor.class));

//                AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, module) ->
//                        builder.method(ElementMatchers.any())
//                        .intercept(Advice.to(AnyAdvice.class));

//        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, module) ->
//                builder.method(ElementMatchers.any())
//                        .intercept(Advice.to(ExceptionAdvice.class));

//        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer.ForAdvice()
//                .with(AgentBuilder.LocationStrategy.ForClassLoader.STRONG)
//                .include(RequestMapping.class.getClassLoader())
//                .with(Assigner.DEFAULT)
//                .withExceptionHandler(new Advice.ExceptionHandler.Simple(Removal.SINGLE))
//                .advice(named("toString"), BarAdvice.class.getName());

        AgentBuilder agentBuilder = new AgentBuilder.Default()
                .with(eager)
                .ignore(ignore)
                .type(filter)
                .transform(transformer);


        agentBuilder.installOn(instrumentation);
    }

}
