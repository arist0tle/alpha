package alpha.geektcp.agent.advice;

import alpha.geektcp.agent.repository.CacheRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.web.bind.annotation.GetMapping;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author tanghaiyang on 2019/11/24 20:54.
 */
public class GetMappingAdvisor {
    private GetMappingAdvisor() {
    }

    @Advice.OnMethodEnter
    public static void onMethodEnter(@Advice.Origin Method method,
                                     @Advice.AllArguments Object[] arguments) {
        GetMapping annotation = method.getAnnotation(GetMapping.class);
        getMappingHandle(annotation);
        if (Objects.nonNull(annotation)) {
            System.out.println("Enter " + method.getName() + " with arguments: " + Arrays.toString(arguments));
        }
    }

    @Advice.OnMethodExit
    public static void onMethodExit(@Advice.Origin Method method,
                                    @Advice.AllArguments Object[] arguments,
                                    @Advice.Return Object ret) {
        GetMapping annotation = method.getAnnotation(GetMapping.class);
        if (Objects.nonNull(annotation)) {
            System.out.println("Exit " + method.getName() + " with arguments: " + Arrays.toString(arguments) + " return: " + ret);
        }
    }

    public static void getMappingHandle(GetMapping annotation) {
        if (Objects.isNull(annotation)) {
            return;
        }

        String[] pathArr  = annotation.value();
        String[] params = annotation.params();
        System.out.println("annotation: " + Arrays.toString(pathArr) + " | " + Arrays.toString(params));
        CacheRepository.put("metric", Arrays.toString(pathArr) );
    }


}