package com.geektcp.alpha.agent.example;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author haiyang.tang on 12.03 003 15:07:50.
 */
public class ByteBuddyTest {

    public static void main(String[] args) {
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

}
