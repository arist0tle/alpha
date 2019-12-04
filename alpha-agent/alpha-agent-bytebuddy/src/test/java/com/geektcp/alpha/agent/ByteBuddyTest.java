package com.geektcp.alpha.agent;

import com.casstime.agent.example.TestService;
import com.casstime.agent.example.advisor.ExceptionAdvisor;
import com.casstime.agent.example.advisor.LoggerAdvisor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.annotation.Annotation;

/**
 * @author haiyang.tang on 12.03 003 15:07:50.
 */
public class ByteBuddyTest {

    public static void main(String[] args) {
//        testByteBuddy();
//        testException();

        try {
            Class cls = Class.forName("com.casstime.agent.annotation.GetMapping");

            System.out.println(cls.getName());
            System.out.println(cls.getSimpleName());
            System.out.println(cls.getAnnotations());
            System.out.println(cls.getFields());
            Class cls3 = Class.forName("com.casstime.agent.annotation.GetMapping");
            Annotation[] annotations = cls.getAnnotations();
            System.out.println(annotations);

            ElementMatcher elementMatcher = ElementMatchers.isAnnotatedWith(cls3);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void testByteBuddy() {
        try {
            TestService testService = new ByteBuddy()
                    .subclass(TestService.class)
                    .method(ElementMatchers.any())
                    .intercept(Advice.to(LoggerAdvisor.class))
                    .make()
                    .load(TestService.class.getClassLoader())
                    .getLoaded()
                    .newInstance();
            testService.bar(123);
            testService.foo(456);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testException() {
        try {
            TestService testService = new ByteBuddy()
                    .subclass(TestService.class)
                    .method(ElementMatchers.any())
                    .intercept(Advice.to(ExceptionAdvisor.class))
                    .make()
                    .load(TestService.class.getClassLoader())
                    .getLoaded()
                    .newInstance();

            testService.exception(123);
            testService.bar(422);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static void testAnnotation() {
        try {
            Class cls = Class.forName("com.casstime.agent.annotation.GetMapping");
            AnnotationDescription annotationDescription = (AnnotationDescription) cls.newInstance();
            AnnotationDescription annotations = (AnnotationDescription) cls.getAnnotation(AnnotationDescription.class);
            AnnotationDescription tt = null;
//            ElementMatchers.annotationType();
            TestService testService = new ByteBuddy()
                    .subclass(TestService.class)
                    .method(ElementMatchers.isAnnotatedWith(ElementMatchers.isAnnotation()))
//                    .method(ElementMatchers.declaresAnnotation)
                    .intercept(Advice.to(LoggerAdvisor.class))
                    .make()
                    .load(TestService.class.getClassLoader())
                    .getLoaded()
                    .newInstance();
            testService.bar(123);
            testService.foo(456);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
