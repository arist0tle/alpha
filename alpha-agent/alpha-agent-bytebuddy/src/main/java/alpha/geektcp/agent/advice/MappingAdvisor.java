package alpha.geektcp.agent.advice;

import alpha.geektcp.agent.repository.CacheRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author tanghaiyang on 2019/11/24 20:54.
 */
public class MappingAdvisor {

    @Advice.OnMethodEnter
    public static void onMethodEnter(@Advice.Origin Method method,
                                     @Advice.AllArguments Object[] arguments) {

        boolean isGet = getMappingHandle(method, GetMapping.class);
//        boolean isPost = getMappingHandle(method, PostMapping.class);
        if (isGet ) {
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

    public static <T extends Annotation> boolean getMappingHandle(Method method, Class<T> annotationClass) {
        Object mapping = method.getAnnotation(annotationClass);
        if (checkAnnotation(method, annotationClass)) {
            return false;
        }
        String[] pathArr = null;
        String[] params = null;
        if (mapping instanceof GetMapping) {
            GetMapping wrapMapping = (GetMapping) mapping;
            pathArr = wrapMapping.value();
            params = wrapMapping.params();
        }
        if (mapping instanceof PostMapping) {
            PostMapping wrapMapping = (PostMapping) mapping;
            pathArr = wrapMapping.value();
            params = wrapMapping.params();
        }
        CacheRepository.put("metric", pathArr );
        System.out.println("annotation: " + Arrays.toString(pathArr) + " | " + Arrays.toString(params));
        return true;
    }

    public static  <T extends Annotation>  boolean checkAnnotation(Method method, Class<T> annotationClass){
        Object mapping = method.getAnnotation(annotationClass);
        if (Objects.isNull(mapping)) {
            return false;
        }
        return true;
    }

}