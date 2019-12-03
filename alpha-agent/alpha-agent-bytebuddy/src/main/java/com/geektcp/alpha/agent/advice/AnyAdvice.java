package com.geektcp.alpha.agent.advice;

import net.bytebuddy.asm.Advice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

public class AnyAdvice {
    /**
     * From this  enter method we start the timer and pass that value to exit method and the we getting the time
     * speed for each method
     */
    @Advice.OnMethodEnter
    static void enter(@Advice.Origin Method method) throws Exception {
        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
        Annotation[] annotations = method.getAnnotations();
        System.out.println("=====annotations=======" + Arrays.toString(annotations));
        System.out.println("=====getDeclaredAnnotations=======" + Arrays.toString(declaredAnnotations));
        System.out.println("=====toString=======" + method.toString());
        System.out.println("=====toGenericString=======" + method.toGenericString());
        System.out.println("=====getName=======" + method.getName());
        System.out.println("=====getDefaultValue=======" + method.getDefaultValue());
    }

}