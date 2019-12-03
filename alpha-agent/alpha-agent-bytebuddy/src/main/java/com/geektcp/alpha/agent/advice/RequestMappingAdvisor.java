package com.geektcp.alpha.agent.advice;

import org.springframework.web.bind.annotation.RequestMapping;
import com.geektcp.alpha.agent.repository.CacheRepository;
import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

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
            System.out.println("annotation: " + Arrays.toString(pathArr) + " | " + Arrays.toString(params));
            String keyCount = String.format("CASS_API_REQUEST_COUNT { path = \"%s\"}", pathArr[0]);
            AtomicLong valueCount = CacheRepository.get(keyCount);
            valueCount.incrementAndGet();
            CacheRepository.put(keyCount, valueCount);

            long end = System.currentTimeMillis();
            long timeCost = end - start;
            String keyPath = String.format("CASS_API_COST_TIME { path = \"%s\"}", pathArr[0]);
            System.out.println("||||||GetMappingAdvisor||||||||" + timeCost + " took " + timeCost + " milliseconds ");
            CacheRepository.put(keyPath, new AtomicLong(timeCost));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}