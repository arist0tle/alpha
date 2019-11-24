package alpha.geektcp.com.agent.bytebuddy.example;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tanghaiyang on 2019/11/24 20:53.
 */
@Slf4j
public class Service {
    private String aaa = "d11111111";
    private static boolean bbb = true;

    @Log
    public int foo(int value) {
        log.info("foo: {} | aaa: {} | bbb: {}",value, aaa, bbb);
        return value;
    }

    public int bar(int value) {
        log.info("bar: {} | aaa: {} | bbb: {}",value, aaa, bbb);
        return value;
    }
}