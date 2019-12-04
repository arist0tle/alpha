package com.geektcp.alpha.agent.advice;

import net.bytebuddy.asm.Advice;

import java.lang.reflect.Method;
import java.util.Objects;

import static com.geektcp.alpha.agent.constant.Metrics.*;

/**
 * @author tanghaiyang on 2019/11/24 20:54.
 */
public class MappingAdvisor {

    private MappingAdvisor() {
    }

    @Advice.OnMethodEnter
    public static long onMethodEnter(@Advice.Origin Method method,
                                     @Advice.AllArguments Object[] arguments) {
        long start = System.currentTimeMillis();
        String path = AdviceUtil.getPath(method);
        String methodStr = AdviceUtil.getMethod(method);
        if(path.length()==0){
            return start;
        }
        AdviceUtil.handleCount(path, methodStr, CASS_REQUEST_COUNT_TOTAL);
        return start;
    }

    @Advice.OnMethodExit(onThrowable = Exception.class)
    public static void onMethodExit(@Advice.Origin Method method,
                                    @Advice.AllArguments Object[] arguments,
                                    @Advice.Return Object ret,
                                    @Advice.Thrown Throwable throwable,
                                    @Advice.Enter long start) {
        String path = AdviceUtil.getPath(method);
        String methodStr = AdviceUtil.getMethod(method);
        if (Objects.nonNull(throwable)) {
            AdviceUtil.handleCount(path, methodStr, CASS_REQUEST_COUNT_ERR);
            return;
        }
        AdviceUtil.handleExit(path, start, CASS_REQUEST_COST_MILLISECONDS);
    }

}