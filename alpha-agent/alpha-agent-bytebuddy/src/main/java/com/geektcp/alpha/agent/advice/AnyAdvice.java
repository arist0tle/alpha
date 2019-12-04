package com.geektcp.alpha.agent.advice;

import net.bytebuddy.asm.Advice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnyAdvice {

    private AnyAdvice() {
    }

    @Advice.OnMethodEnter
    static void enter(@Advice.Origin Method method) throws Exception {
        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
        Annotation[] annotations = method.getAnnotations();
    }

}