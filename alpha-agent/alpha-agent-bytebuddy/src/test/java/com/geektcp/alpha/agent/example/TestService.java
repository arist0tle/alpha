package com.geektcp.alpha.agent.example;

import com.geektcp.alpha.agent.example.annotation.Prometheus;

/**
 * @author haiyang.tang on 12.03 003 15:10:20.
 */
public class TestService {
    @Prometheus
    public int foo(int value) {
        System.out.println("foo: " + value);
        return value;
    }

    public int bar(int value) {
        System.out.println("bar: " + value);
        return value;
    }

    public int exception(int value) {
        System.out.println("bar: " + value);
        testException();
        return value;
    }

    private void testException(){
        throwExceptionTest();
    }
    private void throwExceptionTest(){
        throw new RuntimeException("this is throw RuntimeException by thy");
    }
}
