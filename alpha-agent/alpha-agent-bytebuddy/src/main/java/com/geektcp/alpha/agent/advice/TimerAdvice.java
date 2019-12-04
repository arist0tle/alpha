package com.geektcp.alpha.agent.advice;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;

public class TimerAdvice {

    private TimerAdvice() {
    }

    @Advice.OnMethodEnter
    static long enter(@Advice.Origin Method method) throws Exception {
        return System.currentTimeMillis();
    }

    @Advice.OnMethodExit
    static void exit(@Advice.Origin Method method, @Advice.Enter long start) throws Exception {

        long end = System.currentTimeMillis();
        System.out.println("TimerAdvice: " + method + " took " + (end - start) + " milliseconds ");
    }

}