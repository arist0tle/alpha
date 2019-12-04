package com.geektcp.alpha.agent.advice;

import net.bytebuddy.asm.Advice;

public class ExceptionAdvice {

    private ExceptionAdvice() {
    }

    @Advice.OnMethodEnter
    static long enter(@Advice.Origin String method) throws Exception {
        System.out.println("before");
        return System.currentTimeMillis();
    }

    @Advice.OnMethodExit
    static void exit(@Advice.Origin String method, @Advice.Enter long start) throws Exception {
        System.out.println("end");
        long end = System.currentTimeMillis();
        System.out.println("ExceptionAdvice: " + method + " took " + (end - start) + " milliseconds ");
    }

}