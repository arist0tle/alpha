package com.geektcp.alpha.util.base.proxy.example.subject;

/**
 * Created by TangHaiyang on 2019/8/15.
 */
public class SubjectStaticProxy implements Subject {

    private Subject realSubject = new RealSubject();

    public void doSomething() {
        realSubject.doSomething();
    }

}
