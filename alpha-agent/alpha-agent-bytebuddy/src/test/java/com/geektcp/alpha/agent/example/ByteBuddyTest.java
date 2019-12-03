package com.geektcp.alpha.agent.example;

import com.google.common.annotations.VisibleForTesting;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.ExceptionMethod;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author haiyang.tang on 12.03 003 15:07:50.
 */
public class ByteBuddyTest {

    public static void main(String[] args) {
//        testByteBuddy();
        testException();
    }

    private static void testByteBuddy(){
        try {
            Service service = new ByteBuddy()
                    .subclass(Service.class)
                    .method(ElementMatchers.any())
                    .intercept(Advice.to(LoggerAdvisor.class))
                    .make()
                    .load(Service.class.getClassLoader())
                    .getLoaded()
                    .newInstance();
            service.bar(123);
            service.foo(456);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testException(){
        try {
            Service service = new ByteBuddy()
                    .subclass(Service.class)
                    .method(ElementMatchers.any())
                    .intercept(Advice.to(ExceptionAdvisor.class))
                    .make()
                    .load(Service.class.getClassLoader())
                    .getLoaded()
                    .newInstance();

//            Service service = new ByteBuddy()
//                    .subclass(Service.class)
//                    .method(ElementMatchers.any())
//                    .intercept(ExceptionAdvisor.class,"ddd")
//                    .make()
//                    .load(Service.class.getClassLoader())
//                    .getLoaded()
//                    .newInstance();

            service.exception(123);
            service.bar(422);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
