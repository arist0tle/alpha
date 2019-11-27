package alpha.geektcp.agent.advice;

import net.bytebuddy.asm.Advice;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author tanghaiyang on 2019/11/24 20:54.
 */
public class PostMappingAdvisor {

    private PostMappingAdvisor() {
    }

    @Advice.OnMethodEnter
    public static void onMethodEnter(@Advice.Origin Method method,
                                     @Advice.AllArguments Object[] arguments) {
        PostMapping annotation = method.getAnnotation(PostMapping.class);
        getMappingHandle(annotation);
        if (Objects.nonNull(annotation)) {
            System.out.println("Enter " + method.getName() + " with arguments: " + Arrays.toString(arguments));
        }
    }

    @Advice.OnMethodExit
    public static void onMethodExit(@Advice.Origin Method method,
                                    @Advice.AllArguments Object[] arguments,
                                    @Advice.Return Object ret) {
        PostMapping annotation = method.getAnnotation(PostMapping.class);
        if (Objects.nonNull(annotation)) {
            System.out.println("Exit " + method.getName() + " with arguments: " + Arrays.toString(arguments) + " return: " + ret);
        }
    }

    public static void getMappingHandle(PostMapping annotation) {
        if (Objects.isNull(annotation)) {
            return;
        }
        String[] pathArr = annotation.value();
        String[] params = annotation.params();
        System.out.println("annotation: " + Arrays.toString(pathArr) + " | " + Arrays.toString(params));
    }
}