package com.geektcp.alpha.agent.example;

/**
 * @author haiyang.tang on 12.03 003 15:10:20.
 */
public class Service {
    @Log
    public int foo(int value) {
        System.out.println("foo: " + value);
        return value;
    }

    public int bar(int value) {
        System.out.println("bar: " + value);
        return value;
    }
}
