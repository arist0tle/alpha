package alpha.geektcp.com.agent.bytebuddy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author tanghaiyang on 2019/11/24 17:02.
 */
@Retention(RetentionPolicy.RUNTIME)
@interface Secured {
    String user();
}

class UserHolder {
    static String user;
}

interface Framework {
    <T> T secure(Class<T> type);
}
