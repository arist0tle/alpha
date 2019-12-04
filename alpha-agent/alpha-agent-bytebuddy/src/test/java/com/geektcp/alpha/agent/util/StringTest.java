package com.geektcp.alpha.agent.util;

/**
 * @author haiyang.tang on 11.14 014 17:32:36.
 */
public class StringTest {
    public static void main(String[] args) {
        String a = "com.geektcp.alpha.dingtalk.controller.TestController.test";
        if (a.startsWith("com.geektcp.alpha.dingtalk.controller")) {
            System.out.println(1111111);
        }
    }
}
