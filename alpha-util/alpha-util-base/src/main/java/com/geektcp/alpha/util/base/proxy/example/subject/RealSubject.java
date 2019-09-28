package com.geektcp.alpha.util.base.proxy.example.subject;

/**
 * @author tanghaiyang on 2019/8/15.
 */
public class RealSubject implements Subject {

    public void doSomething() {
        System.out.println("call doSomething()");
    }

}
