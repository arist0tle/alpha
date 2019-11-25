package alpha.geektcp.com.agent.bytebuddy.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author tanghaiyang on 2019/11/24 20:52.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value() default "/";

    boolean readOnly() default true;

}