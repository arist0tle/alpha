package com.geektcp.alpha.agent.advice;

import com.geektcp.alpha.agent.builder.ThyCacheBuilder;
import net.bytebuddy.asm.Advice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import static com.casstime.agent.constant.AgentMethod.*;

/**
 * @author haiyang.tang on 12.03 003 16:40:22.
 */
public class AdviceUtil {

    private AdviceUtil() {
    }

    public static void handleExit(String path, @Advice.Enter long start, String metric) {
        try {
            long end = System.currentTimeMillis();
            long timeCost = end - start;
            String keyPath = String.format("%s { path = \"%s\"}", metric, path);
            ThyCacheBuilder.incrementAndGet(keyPath, timeCost);
            ThyCacheBuilder.incrementAndGet(metric, timeCost);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void handleCount(String path, String method, String metric) {
        try {
            String keyPath = String.format("%s { method = \"%s\" , path = \"%s\"}", metric, method, path);
            ThyCacheBuilder.incrementAndGet(keyPath);
            ThyCacheBuilder.incrementAndGet(metric);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getPath(Method method) {
        String path = "";
        try {
            for (Annotation annotationElement : method.getAnnotations()) {
                String simpleName = annotationElement.annotationType().getSimpleName();
                if (simpleName.contains(GET_MAPPING)
                        || simpleName.contains(POST_MAPPING)
                        || simpleName.contains(REQUEST_MAPPING)
                ) {
                    String[] pathArr = (String[]) annotationElement.getClass().getMethod(VALUE).invoke(annotationElement);
                    path = Arrays.toString(pathArr);
                    System.out.println("path:" + path);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return path;
    }

    public static String getMethod(Method method) {
        try {
            for (Annotation annotationElement : method.getAnnotations()) {
                String simpleName = annotationElement.annotationType().getSimpleName();
                if (simpleName.contains(REQUEST_MAPPING)
                ) {
                    String[] methodArr = (String[]) annotationElement.getClass().getMethod(METHOD).invoke(annotationElement);
                    return Arrays.toString(methodArr);
                }
                if(simpleName.contains(GET_MAPPING)){
                    return GET;
                }
                if(simpleName.contains(POST_MAPPING)){
                    return POST;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return DEFAULT;
    }
}
