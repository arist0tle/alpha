package com.geektcp.alpha.util.base.proxy.example.bird;

import org.junit.Test;

/**
 * Created by TangHaiyang on 2019/8/15.
 */
public class Client {
    public static void main(String[] args) {
        Bird bird = new Bird();
        long start = System.currentTimeMillis();
        bird.fly();
        long end = System.currentTimeMillis();
        System.out.println("Fly time = " + (end - start));
    }

    @Test
    public void BirdLogProxyTest(){
        Bird bird = new Bird();
        BirdLogProxy p1 = new BirdLogProxy(bird);
        Bird4 p2 = new Bird4(p1);

        p2.fly();
    }

    @Test
    public void BirdLogProxyTest2(){
        Bird bird = new Bird();
        Bird4 p2 = new Bird4(bird);
        BirdLogProxy p1 = new BirdLogProxy(p2);

        p1.fly();
    }
}
