package com.geektcp.alpha.agent.advice;

import com.geektcp.alpha.agent.builder.ThyCacheBuilder;
import net.bytebuddy.asm.Advice;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import static com.geektcp.alpha.agent.constant.Metrics.*;

/**
 * @author tanghaiyang on 2019/11/24 20:54.
 */
public class RequestMappingAdvisor {
    private RequestMappingAdvisor() {
    }

    @Advice.OnMethodEnter
    public static long onMethodEnter(@Advice.Origin Method method,
                                     @Advice.AllArguments Object[] arguments) {
        RequestMapping annotation = method.getAnnotation(RequestMapping.class);

        long start = System.currentTimeMillis();
        getMappingHandle(annotation, start);
        if (Objects.nonNull(annotation)) {
            System.out.println("Enter " + method.getName() + " with arguments: " + Arrays.toString(arguments));
        }

        return start;
    }

    @Advice.OnMethodExit
    public static void onMethodExit(@Advice.Origin Method method,
                                    @Advice.AllArguments Object[] arguments,
                                    @Advice.Return Object ret) {
        RequestMapping annotation = method.getAnnotation(RequestMapping.class);
        if (Objects.nonNull(annotation)) {
            System.out.println("Exit " + method.getName() + " with arguments: " + Arrays.toString(arguments) + " return: " + ret);
        }
    }

    public static void getMappingHandle(RequestMapping annotation,@Advice.Enter long start) {
        if (Objects.isNull(annotation)) {
            return;
        }
        try {
            String[] pathArr = annotation.value();
            String[] params = annotation.params();

            System.out.println("RequestMapping annotation: " + Arrays.toString(pathArr) +
                    " | " + Arrays.toString(params) + " | " + Arrays.toString(annotation.method()));

            String keyCount = String.format("%s { path = \"%s\"}",CASS_API_REQUEST_COUNT, pathArr[0]);
            AtomicLong valueCount = ThyCacheBuilder.incrementAndGet(keyCount);
            ThyCacheBuilder.put(keyCount, valueCount);

            long end = System.currentTimeMillis();
            long timeCost = end - start;
            String keyPath = String.format("%s { path = \"%s\"}", CASS_API_COST_TIME_MILLISECONDS, pathArr[0]);
            System.out.println("||||||RequestMapping||||||||" + timeCost + " took " + timeCost + " milliseconds ");
            ThyCacheBuilder.put(keyPath, new AtomicLong(timeCost));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}