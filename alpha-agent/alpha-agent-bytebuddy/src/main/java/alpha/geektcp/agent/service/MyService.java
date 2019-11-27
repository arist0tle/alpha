package alpha.geektcp.agent.service;

import alpha.geektcp.agent.annotation.Log;

/**
 * @author tanghaiyang on 2019/11/24 20:53.
 */
public class MyService {
    private String aaa = "d11111111";
    private static boolean bbb = true;

    @Log
    public int foo(int value) {
        System.out.println(String.format("foo: {} | aaa: {} | bbb: {}",value, aaa, bbb));
        return value;
    }

    public int bar(int value) {
        System.out.println(String.format("bar: {} | aaa: {} | bbb: {}",value, aaa, bbb));
        return value;
    }
}