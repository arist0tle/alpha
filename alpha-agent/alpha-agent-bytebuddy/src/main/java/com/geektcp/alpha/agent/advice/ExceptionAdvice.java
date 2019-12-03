package com.geektcp.alpha.agent.advice;

import net.bytebuddy.asm.Advice;

public class ExceptionAdvice {
    /**
     * From this  enter method we start the timer and pass that value to exit method and the we getting the time
     * speed for each method
     */
    @Advice.OnMethodEnter
    static long enter(@Advice.Origin String method) throws Exception {
        System.out.println("异常处理前");
        long start = System.currentTimeMillis();
        return start;
    }

    @Advice.OnMethodExit
    static void exit(@Advice.Origin String method, @Advice.Enter long start) throws Exception {
        System.out.println("异常处理后");
        long end = System.currentTimeMillis();
        System.out.println("||||||TimerAdvice||||||||" + method + " took " + (end - start) + " milliseconds ");
    }

}