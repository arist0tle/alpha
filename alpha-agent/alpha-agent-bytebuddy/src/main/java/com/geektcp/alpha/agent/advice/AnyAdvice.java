package com.geektcp.alpha.agent.advice;

import net.bytebuddy.asm.Advice;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import static com.geektcp.alpha.agent.util.LogUtil.log;

public class AnyAdvice {

    private AnyAdvice() {
    }

    @Advice.OnMethodEnter
    static void enter(@Advice.Origin Method method){
        if(method.getName().equals("getTemplateDTO")){
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            Annotation[] annotations = method.getAnnotations();
            log("<<<<<<<<<<<");
            log("annotations" + Arrays.toString(annotations));
            log("annotations" + Arrays.toString(declaredAnnotations));
            log(">>>>>>>>>>>");
        }
    }

}