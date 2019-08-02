package com.geektcp.alpha.util.base;

import org.junit.Test;
import sun.security.krb5.internal.crypto.HmacMd5ArcFourCksumType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by TangHaiyang on 2019/8/2.
 * 验证基础数据类型
 * 继承的执行顺序
 * CountDownLatch的执行效果
 */
public class JavaUtitl {
    @Test
    public void test() {
//        float f = 1.1;
//        long a = 10;
//        int  b = a;
        short s1 = 0;
        s1 += 1;
        String s = "11";
        Object o = new Object();

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
//        map.put()


        // FIXME: 2019/8/2


    }

    @Test
    public void countDown() throws Throwable{
        final int totalThread = 10;
        CountDownLatch countDownLatch = new CountDownLatch(totalThread);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            executorService.execute(() -> {
                System.out.print("run..");
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("end");
        executorService.shutdown();
    }

    class A {

        public void show(A obj) {
            System.out.println("A.show(A)");
        }

        public void show(C obj) {
            System.out.println("A.show(C)");
        }
    }

    class B extends A {

        @Override
        public void show(A obj) {
            System.out.println("B.show(A)");
        }
    }

    class C extends B {
    }

    class D extends C {
    }


    @Test
    public void testClass() {
        A a = new A();
        B b = new B();
        C c = new C();
        D d = new D();

        // 在 A 中存在 show(A obj)，直接调用
        a.show(a); // A.show(A)
        // 在 A 中不存在 show(B obj)，将 B 转型成其父类 A
        a.show(b); // A.show(A)
        // 在 B 中存在从 A 继承来的 show(C obj)，直接调用
        b.show(c); // A.show(C)
        // 在 B 中不存在 show(D obj)，但是存在从 A 继承来的 show(C obj)，将 D 转型成其父类 C
        b.show(d); // A.show(C)

        // 引用的还是 B 对象，所以 ba 和 b 的调用结果一样
        A ba = new B();
        ba.show(c); // A.show(C)
        ba.show(d); // A.show(C)
    }


}
