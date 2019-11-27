package alpha.geektcp.agent.advice;

import alpha.geektcp.agent.annotation.Log;
import net.bytebuddy.asm.Advice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author tanghaiyang on 2019/11/24 20:54.
 */
public class LoggerAdvisor {

    @Advice.OnMethodEnter
    public static void onMethodEnter(@Advice.Origin Method method,
                                     @Advice.AllArguments Object[] arguments,
                                     @Advice.FieldValue(value = "aaa", readOnly = false) String aaa,
                                     @Advice.FieldValue(value = "bbb", readOnly = false) boolean bbb) {
        Log annotation =  method.getAnnotation(Log.class);
        if (Objects.nonNull(annotation)) {
           boolean readOnly =  annotation.readOnly();
            System.out.println("Enter " + method.getName() + " with arguments: " + Arrays.toString(arguments));
            System.out.println("field aaa: " + aaa + " | bbb: " + bbb);
        }
    }

    @Advice.OnMethodExit
    public static void onMethodExit(@Advice.Origin Method method, @Advice.AllArguments Object[] arguments,
                                    @Advice.Return Object ret,
                                    @Advice.FieldValue(value = "aaa", readOnly = false) String aaa,
                                    @Advice.FieldValue(value = "bbb", readOnly = false) boolean bbb) {
        Annotation annotation =  method.getAnnotation(Log.class);
        if (Objects.nonNull(annotation)) {
            System.out.println("Exit " + method.getName() + " with arguments: " + Arrays.toString(arguments) + " return: " + ret);
            System.out.println("field aaa: " + aaa + " | bbb: " + bbb);
        }
    }
}